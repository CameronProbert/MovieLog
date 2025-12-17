package com.example.movielogger.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielogger.data.movie.IMovieRepo
import com.example.movielogger.data.movie.Movie
import com.example.movielogger.data.movie.MovieSummary
import com.example.movielogger.ui.view.MovieScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepo: IMovieRepo
) : ViewModel() {
    private val _movieList = MutableStateFlow<List<MovieSummary>?>(null)
    val movieList: StateFlow<List<MovieSummary>?> get() = _movieList.asStateFlow()

    private val _selectedView = MutableStateFlow(MovieScreenState.LIST)
    val selectedView: StateFlow<MovieScreenState> = _selectedView.asStateFlow()

    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie.asStateFlow()

    init {
        viewModelScope.launch {
            getAllMovies()
        }
    }

    suspend fun getAllMovies() {
        val getAllMoviesRes = movieRepo.getAll()
        if (getAllMoviesRes.isSuccess) {
            _movieList.tryEmit(getAllMoviesRes.getOrNull())
        } else {
            getAllMoviesRes.getOrElse { Log.e("MovieListViewModel", "getAllMovies(): ${it.message}", it) }
        }
    }

    fun newMovie() {
        _selectedView.tryEmit(MovieScreenState.DETAIL)
        _selectedMovie.tryEmit(Movie(title = ""))
    }

    fun editMovie(summary: MovieSummary) {
        viewModelScope.launch {
            val getMovieRes = movieRepo.get(summary.id)
            if (getMovieRes.isSuccess) {
                _selectedView.tryEmit(MovieScreenState.DETAIL)
                _selectedMovie.tryEmit(getMovieRes.getOrNull())
            } else {
                getMovieRes.getOrElse { Log.e("MovieListViewModel", "editMovie(): ${it.message}", it) }
            }
        }
    }
}