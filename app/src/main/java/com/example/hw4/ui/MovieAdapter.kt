package com.example.hw4.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.hw4.databinding.ItemMovieBinding
import com.example.hw4.model.data.Movie
import com.example.hw4.model.source.Database

class MovieAdapter(
    private val onMovieClick: (Movie) -> Unit,
    private val onCheckedChange: () -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movieList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movieList[position])


    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(movie: Movie) {

            with(binding) {

                tvMovieName.text = movie.title
                tvMovieRate.text = movie.rating
                tvDateAdded.text = movie.date

                if(movie.isWatched){
                    checkbox.isChecked = true
                }

                checkbox.setOnCheckedChangeListener { _, isChecked ->
                    Database.removeFromMovieList(movie)
                    var updatedMovie = Movie(
                        id = movie.id,
                        title = movie.title,
                        genre = movie.genre,
                        rating = movie.rating,
                        year = movie.year,
                        isWatched = !movie.isWatched,
                        date = movie.date
                    )
                    Database.addToMovieList(
                        updatedMovie.title,
                        updatedMovie.genre,
                        updatedMovie.year,
                        updatedMovie.rating,
                        updatedMovie.isWatched,
                        updatedMovie.date
                    )
                    onCheckedChange()
                }



                root.setOnClickListener {
                    onMovieClick(movie)
                }

            }
        }
    }

    fun updateList(list: List<Movie>) {
        movieList.clear()
        movieList.addAll(list)
        notifyItemRangeChanged(0, list.size)
    }

    override fun getItemCount() = movieList.size


}







