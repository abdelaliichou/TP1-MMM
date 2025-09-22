package com.example.tp1singleviewapp.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tp1singleviewapp.model.Utils
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "concerts")
data class Concert (
    @PrimaryKey(
        autoGenerate = true
    ) val id: Int = 0,
    val group: String,
    val date: String,
    val nationality: String,
    val location: String,
    val price: String,
    val ticketsLeft: String,
    val favorite: Boolean,
    val Image: String
): Parcelable