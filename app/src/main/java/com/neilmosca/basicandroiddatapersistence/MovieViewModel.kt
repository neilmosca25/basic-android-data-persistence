package com.neilmosca.basicandroiddatapersistence

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _viewState = MutableStateFlow(MovieViewState())
    val viewState: StateFlow<MovieViewState> = _viewState

    fun handleIntent(intent: MovieIntent) {
        when (intent) {
            is MovieIntent.GetMovies -> getMovies()
            is MovieIntent.GetMovie -> getMovie(intent.id)
            is MovieIntent.CreateMovie -> createMovie(intent.movie)
            is MovieIntent.UpdateMovie -> updateMovie(intent.movie)
            is MovieIntent.DeleteMovie -> deleteMovie(intent.id)
        }
    }

    init {
        getMovies()
    }


    private fun getMovies() {
        viewModelScope.launch {
            _viewState.update { it.copy(isLoading = true) }
            try {
                val movies = movieRepository.getMovies()
                _viewState.update { it.copy(movies = movies.toMutableList(), isLoading = false, hasError = false) }
            } catch (e: Exception) {
                _viewState.update { it.copy(isLoading = false, hasError = true) }
            }
        }
    }

    private fun getMovie(id: Long) {
        viewModelScope.launch {
            try {
                val movie = movieRepository.getMovie(id)
                // Handle single movie result (e.g., update state or navigate)
            } catch (e: Exception) {
                _viewState.update { it.copy(hasError = true) }
            }
        }
    }

    private fun createMovie(movie: Movie) {
        viewModelScope.launch {
            try {
                val isSuccessful = movieRepository.createMovie(movie)
                if (isSuccessful) getMovies()
            } catch (e: Exception) {
                _viewState.update { it.copy(hasError = true) }
            }
        }
    }

    private fun updateMovie(movie: Movie) {
        viewModelScope.launch {
            try {
                val isSuccessful = movieRepository.updateMovie(movie)
                if (isSuccessful) getMovies()
            } catch (e: Exception) {
                _viewState.update { it.copy(hasError = true) }
            }
        }
    }

    private fun deleteMovie(id: Long) {
        viewModelScope.launch {
            try {
                val isSuccessful = movieRepository.deleteMovie(id)
                if (isSuccessful) {
                    getMovies()
                } else {
                    _viewState.update { it.copy(hasError = true) }
                }
            } catch (e: Exception) {
                _viewState.update { it.copy(hasError = true) }
            }
        }
    }
}