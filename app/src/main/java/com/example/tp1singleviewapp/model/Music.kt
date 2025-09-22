package com.example.tp1singleviewapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Music (
    val group: String,
    val date: String,
    val nationality: String,
    val location: String,
    val price: String,
    val ticketsLeft: String,
    val favorite: Boolean,
    val Image: String
): Parcelable

