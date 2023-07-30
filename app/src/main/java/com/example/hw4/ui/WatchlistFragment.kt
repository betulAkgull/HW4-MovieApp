package com.example.hw4.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hw4.R
import com.example.hw4.databinding.FragmentWatchlistBinding
import com.example.hw4.model.data.Movie
import com.example.hw4.model.source.Database
import com.example.movieapp.common.viewBinding

class WatchlistFragment : Fragment(R.layout.fragment_watchlist) {

    private val binding by viewBinding(FragmentWatchlistBinding::bind)
    private val movieAdapter = MovieAdapter(::onMovieClick, ::onCheckedChange)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            setData()
        }
    }

    fun setData() {
        Database.isWatched()
        binding.rvWatchlist.adapter = movieAdapter
        movieAdapter.updateList(Database.getWatchlist())

    }

    fun onMovieClick(movie: Movie) {
        val action = WatchlistFragmentDirections.watchlistToDetails(movie)
        findNavController().navigate(action)
    }

    private fun onCheckedChange() {
        movieAdapter.updateList(Database.getWatchlist())
    }
}