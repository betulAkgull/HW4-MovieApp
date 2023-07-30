package com.example.hw4.model.source

import com.example.hw4.model.data.Movie

object Database {

    private val movieList = mutableListOf<Movie>()
    private val watchlist = mutableListOf<Movie>()
    private val ratingslist = mutableListOf<Movie>()

    fun getMovieList() = movieList
    fun getWatchlist() = watchlist
    fun getRatingsList() = ratingslist

    fun addToMovieList(
        title: String,
        genre: String,
        year: String,
        rating: String,
        isWatched: Boolean,
        date: String
    ) {
        movieList.add(
            Movie(
                id = (movieList.lastOrNull()?.id ?: 1) + 1,
                title = title,
                genre = genre,
                year = year,
                rating = rating,
                isWatched = isWatched,
                date = date
            )
        )
    }
    fun isWatched() {
        ratingslist.clear()
        watchlist.clear()

        movieList.forEach {
            if (it.isWatched) {
                ratingslist.add(it)
            } else {
                watchlist.add(it)
            }
        }
    }

    fun removeFromMovieList(movie: Movie) = movieList.remove(movie)
}