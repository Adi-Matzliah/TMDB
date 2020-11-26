package com.exercise.firstdigitalbank.tmdb.feature.details

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.exercise.firstdigitalbank.tmdb.data.RemoteRepository
import com.exercise.firstdigitalbank.tmdb.data.model.MovieDescription
import kotlinx.coroutines.launch

class MovieDetailsViewModel @ViewModelInject constructor(
    private val repository: RemoteRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDescription>()
    val movieDetails: LiveData<MovieDescription>
        get() = _movieDetails

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _movieDetails.value = repository.getMovieDetails(movieId)
        }
    }
}