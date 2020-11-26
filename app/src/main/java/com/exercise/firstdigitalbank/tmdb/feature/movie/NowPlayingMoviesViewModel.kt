package com.exercise.firstdigitalbank.tmdb.feature.movie

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

class NowPlayingMoviesViewModel@ViewModelInject constructor(
    private val repository: RemoteRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private lateinit var moviesResult: Flow<PagingData<Movie>>

    fun fetchMoviesCategoryFlow(movieCategory: MovieCategory): Flow<PagingData<Movie>> {
        moviesResult = repository.getMoviesByCategory(movieCategory)
            .cachedIn(viewModelScope)
        return moviesResult
    }
}