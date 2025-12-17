package com.example.movielogger.ui.view

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movielogger.data.movie.MovieSummary
import com.example.movielogger.ui.viewmodel.MovieListViewModel

@Composable
fun MovieScreen(
    movieListViewModel: MovieListViewModel = viewModel()
) {
    val movies = movieListViewModel.movieList.collectAsStateWithLifecycle()
    val selectedView = movieListViewModel.selectedView.collectAsStateWithLifecycle()

    var selectedMovie by remember { mutableStateOf<MovieSummary?>(null) }

    val onMovieSelect = { movie: MovieSummary ->
        if (selectedMovie == movie) {
            selectedMovie = null
        } else {
            selectedMovie = movie
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Surface(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp), color = MaterialTheme.colorScheme.background) {
                Text(textAlign = TextAlign.Center, text = "Movie Log")
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (selectedMovie != null) {
                    movieListViewModel.editMovie(selectedMovie!!)
                } else {
                    movieListViewModel.newMovie()
                }
            }) {
                Text(text = if (selectedMovie == null) "New Movie" else "Edit Movie")
            }
        },
    ) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(top = it.calculateTopPadding())) {
            if (selectedView.value == MovieScreenState.LIST) {
                MovieListScreen(
                    movies = movies.value ?: emptyList(),
                    onMovieSelect = onMovieSelect,
                    selectedMovie = selectedMovie,
                )
            } else {
                Text(selectedMovie?.title ?: "New Movie")
            }
        }
    }
}

enum class MovieScreenState { LIST, DETAIL }