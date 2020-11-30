package com.exercise.firstdigitalbank.tmdb.feature.details

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingSource
import com.exercise.firstdigitalbank.tmdb.data.RemoteRepository
import com.exercise.firstdigitalbank.tmdb.data.model.MovieDescription
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class MovieDetailsViewModel @ViewModelInject constructor(
    private val repository: RemoteRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDescription>()
    val movieDetails: LiveData<MovieDescription>
        get() = _movieDetails

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                _movieDetails.value = repository.getMovieDetails(movieId)
            } catch (exception: IOException) {
                Timber.e("IOException: ${exception.message}")
                _errorMessage.value = exception.message
            } catch (exception: HttpException) {
                Timber.e("HttpException: ${exception.message}")
                _errorMessage.value = exception.message

            }
        }
    }
}