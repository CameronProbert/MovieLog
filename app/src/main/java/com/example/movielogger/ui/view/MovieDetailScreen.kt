package com.example.movielogger.ui.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.movielogger.data.movie.Movie

@Composable
fun MovieDetailScreen(selectedMovie: Movie) {
    Text(selectedMovie.title.ifEmpty { "New Movie" })
}