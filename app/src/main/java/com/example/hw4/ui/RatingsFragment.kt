package com.example.hw4.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hw4.R
import com.example.hw4.databinding.FragmentRatingsBinding
import com.example.hw4.databinding.FragmentWatchlistBinding
import com.example.hw4.model.data.Movie
import com.example.hw4.model.source.Database
import com.example.movieapp.common.viewBinding

class RatingsFragment : Fragment(R.layout.fragment_ratings) {

    private val binding by viewBinding(FragmentRatingsBinding::bind)

    private val movieAdapter = MovieAdapter(::onMovieClick, ::onCheckedChange)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            setData()
        }
    }

    fun setData() {
        Database.isWatched()
        binding.rvRatingsList.adapter = movieAdapter
        movieAdapter.updateList(Database.getRatingsList())

    }

    fun onMovieClick(movie: Movie) {
        val action = RatingsFragmentDirections.ratingsToDetails(movie)
        findNavController().navigate(action)
    }

    private fun onCheckedChange() {
        movieAdapter.updateList(Database.getRatingsList())
    }
}