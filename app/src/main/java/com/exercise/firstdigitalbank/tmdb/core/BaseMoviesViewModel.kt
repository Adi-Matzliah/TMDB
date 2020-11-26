package com.exercise.firstdigitalbank.tmdb.core

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.exercise.firstdigitalbank.tmdb.data.RemoteRepository
import com.exercise.firstdigitalbank.tmdb.data.model.Movie
import com.exercise.firstdigitalbank.tmdb.data.model.MovieCategory
import kotlinx.coroutines.flow.Flow

open class BaseMoviesViewModel @ViewModelInject constructor(
    private val repository: RemoteRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private lateinit var moviesResult: Flow<PagingData<Movie>>

    fun fetchMoviesCategoryFlow(movieCategory: MovieCategory): Flow<PagingData<Movie>> {
        //viewModelScope.launch(Dispatchers.IO) {
        //viewModelScope.launch(Dispatchers.IO) {
        moviesResult = repository.getMoviesByCategory(movieCategory)
            .cachedIn(viewModelScope)
        //}
        return moviesResult
        //}
    }
}