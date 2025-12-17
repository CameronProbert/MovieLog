package com.example.movielogger.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movielogger.data.movie.IMovieRepo
import com.example.movielogger.data.movie.MovieSummary
import com.example.movielogger.ui.view.MovieScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepo: IMovieRepo
) : ViewModel() {
    private val _movieList = MutableStateFlow<List<MovieSummary>?>(null)
    val movieList: StateFlow<List<MovieSummary>?> get() = _movieList.asStateFlow()

    private val _selectedView = MutableStateFlow(MovieScreenState.LIST)
    val selectedView: StateFlow<MovieScreenState> = _selectedView.asStateFlow()

    private val _selectedMovie = MutableStateFlow<MovieSummary?>(null)
    val selectedMovie: StateFlow<MovieSummary?> = _selectedMovie.asStateFlow()

    init {
        getAllMovies()
    }

    fun getAllMovies() {
        _movieList.tryEmit(movieRepo.getAll())
    }

    fun newMovie() {
        _selectedView.tryEmit(MovieScreenState.DETAIL)
        _selectedMovie.tryEmit(null)
    }

    fun editMovie(summary: MovieSummary) {
        _selectedView.tryEmit(MovieScreenState.DETAIL)
        _selectedMovie.tryEmit(summary)
    }
}