package com.example.movielogger.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.movielogger.data.DataSource

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MovieListScreen() {
    val movies = DataSource().loadMovies()
    Column {
        HorizontalDivider()
        for (movie in movies) {
            MovieSummary(movie.summary)
            HorizontalDivider()
        }
    }
}