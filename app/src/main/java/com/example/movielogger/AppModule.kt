package com.example.movielogger

import android.content.Context
import androidx.room.Room
import com.example.movielogger.data.AppDatabase
import com.example.movielogger.data.movie.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val MOVIE_DATABASE_NAME: String = "movie_logger_db"

/**
 * Providers for dependency injection.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context : Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, MOVIE_DATABASE_NAME)
            .fallbackToDestructiveMigration(false)
            .build()

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao()
    }
}