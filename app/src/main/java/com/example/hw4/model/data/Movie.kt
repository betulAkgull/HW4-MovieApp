package com.example.hw4.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val genre: String,
    val year: String,
    val rating: String,
    val isWatched: Boolean,
    val date: String
) : Parcelable





