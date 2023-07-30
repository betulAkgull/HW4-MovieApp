package com.example.hw4.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hw4.R
import com.example.hw4.databinding.AlertDialogDeleteBinding
import com.example.hw4.databinding.FragmentDetailsBinding
import com.example.hw4.model.source.Database
import com.example.movieapp.common.viewBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {


            val movieDetails = args.movie

            tvMovieName.text = movieDetails.title
            tvMovieGenre.text = movieDetails.genre
            tvMovieYear.text = movieDetails.year
            tvMovieRating.text = movieDetails.rating


            btnDeleteMovie.setOnClickListener {

                val builder = AlertDialog.Builder(requireContext())
                val binding = AlertDialogDeleteBinding.inflate(layoutInflater)
                builder.setView(binding.root)
                val dialog = builder.create()


                with(binding) {

                    btnCancel.setOnClickListener {
                        dialog.dismiss()
                    }

                    btnDelete.setOnClickListener {
                        Database.removeFromMovieList(movieDetails)
                        Database.isWatched()
                        dialog.dismiss()

                        if(movieDetails.isWatched){
                            findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToRatingsFragment())
                        }else{
                            findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToWatchlistFragment())
                        }
                    }
                }

                dialog.show()

            }



        }

    }



}
