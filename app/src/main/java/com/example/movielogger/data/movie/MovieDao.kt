package com.example.movielogger.data.movie

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MovieDao {

    @Query("SELECT id, title, date_viewed, rating FROM movie")
    fun getAll(): List<MovieDbo>

    @Query("SELECT * FROM movie WHERE id IN (:movieIds)")
    fun loadAllByIds(vararg movieIds: String): List<MovieDbo>

    @Query("SELECT * FROM movie WHERE title LIKE :title LIMIT 5")
    fun findByTitle(title: String): List<MovieDbo>

    @Update
    fun update(movie: MovieDbo)

    @Insert
    fun insertAll(vararg movies: MovieDbo)

    @Delete
    fun delete(movie: MovieDbo)
}