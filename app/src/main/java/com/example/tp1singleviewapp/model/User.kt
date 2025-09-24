package com.example.tp1singleviewapp.model

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@Parcelize
@IgnoreExtraProperties
data class User (
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val number: String = "",
    var country: String = "",
    val birthday: String = ""
): Parcelable