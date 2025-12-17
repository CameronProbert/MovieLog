package com.example.movielogger.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movielogger.data.DataSource
import com.example.movielogger.data.movie.MovieSummary
import com.example.movielogger.ui.theme.MovieLoggerTheme

@Composable
fun MovieListScreen(
    movies: List<MovieSummary>,
    onMovieSelect: (MovieSummary) -> Unit,
    selectedMovie: MovieSummary?
) {
    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalDivider()
        for (movie in movies) {
            MovieListItem(
                summary = movie,
                onSelect = onMovieSelect,
                selectedMovie = selectedMovie?.id == movie.id
            )
            HorizontalDivider()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMovieListScreen() {
    MovieLoggerTheme {
        MovieListScreen(movies = DataSource.loadMovies().map { it.summary }, onMovieSelect = {}, selectedMovie = null)
    }
}