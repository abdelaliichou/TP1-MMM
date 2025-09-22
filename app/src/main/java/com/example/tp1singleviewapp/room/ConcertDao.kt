package com.example.tp1singleviewapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ConcertDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(music: Concert)

    @Update
    fun update(music: Concert)

    @Delete
    fun delete(music: Concert)

    @Query("SELECT * FROM concerts ORDER BY date ASC")
    fun getAllConcerts(): List<Concert>

    @Query("SELECT * FROM concerts WHERE id = :id LIMIT 1")
    fun getConcertById(id: Int): List<Concert>

}