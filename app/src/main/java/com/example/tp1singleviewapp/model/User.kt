package com.example.tp1singleviewapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    val name: String,
    val surname: String,
    val email: String,
    val number: String,
    val country: String,
    val birthday: String
): Parcelable
