package com.example.movielogger.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movielogger.data.movie.MovieDao
import com.example.movielogger.data.movie.MovieDbo

@Database(entities = [MovieDbo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}