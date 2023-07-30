package com.example.hw4.ui

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.hw4.R
import com.example.hw4.databinding.ActivityMainBinding
import com.example.hw4.databinding.AlertDialogBinding
import com.example.hw4.model.source.Database
import com.example.movieapp.common.showDatePicker
import com.example.movieapp.common.viewBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)

            fabAddMovie.setOnClickListener {
                showAddDialog()
            }

        }
    }



    private fun showAddDialog() {
        val builder = AlertDialog.Builder(this)
        val binding = AlertDialogBinding.inflate(layoutInflater)
        builder.setView(binding.root)
        val dialog = builder.create()

        val saveTypeList = listOf("Watchlist", "Ratings")
        var selectedSaveType = false

        val genreList = listOf(
            "Action",
            "Animation",
            "Biography",
            "Comedy",
            "Drama",
            "Horror",
            "Romance",
            "Sci-Fi",
            "Thriller"
        )


        var selectedGenre = ""

        var dateAdded = ""

        var rating = ""

        val saveTypeAdapter = ArrayAdapter(
            this,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            saveTypeList
        )

        val genreAdapter = ArrayAdapter(
            this,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            genreList
        )



        with(binding) {

            actSaveType.setAdapter(saveTypeAdapter)

            actSaveType.setOnItemClickListener { _, _, position, _ ->
                if (saveTypeList[position] == "Watchlist") {
                    selectedSaveType = false
                } else if (saveTypeList[position] == "Ratings") {
                    selectedSaveType = true
                }
            }

            actGenre.setAdapter(genreAdapter)

            actGenre.setOnItemClickListener { _, _, position, _ ->
                selectedGenre = genreList[position]
            }

            val calendar = Calendar.getInstance()

            switchDate.setOnCheckedChangeListener { _, isChecked ->

                if (isChecked) {
                    showDatePicker(this@MainActivity,calendar) { year, month, day ->
                        dateAdded = "$day/$month/$year"
                    }

                }

            }

            btnAdd.setOnClickListener {
                val title = etMovieTitle.text.toString()
                val year = etMovieYear.text.toString()
                rating = ratingBar.rating.toString()


                if (title.isNotEmpty() && year.isNotEmpty() && rating.isNotEmpty() && selectedGenre.isNotEmpty()) {

                    Database.addToMovieList(
                        title,
                        selectedGenre,
                        year,
                        rating,
                        selectedSaveType,
                        dateAdded
                    )

                    dialog.dismiss()
                }
            }

            btnDelete.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }
}