package com.example.movieapp.common

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.fragment.app.Fragment
import java.util.Calendar

fun showDatePicker(
    context: Context,
    calendar: Calendar,
    onDateSelected: (Int, Int, Int) -> Unit
) {
    DatePickerDialog(
        context,
        { _, year, month, day ->
            onDateSelected(year, month, day)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}
