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
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

class MoviesViewModel @ViewModelInject constructor(
    private val repository: RemoteRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private lateinit var nowPlayingMoviesResult: Flow<PagingData<Movie>>
    private lateinit var popularMoviesResult: Flow<PagingData<Movie>>
    private lateinit var topRatedMoviesResult: Flow<PagingData<Movie>>
    private lateinit var upcomingMoviesResult: Flow<PagingData<Movie>>

    fun fetchMoviesCategoryFlow(movieCategory: MovieCategory): Flow<PagingData<Movie>> {
        val moviesResult = repository.getMoviesByCategory(movieCategory)
            .onEach { Timber.d("new page: $it") }
            .cachedIn(viewModelScope)

        when (movieCategory) {
            MovieCategory.NOW_PLAYING -> {
                nowPlayingMoviesResult = moviesResult
            }
            MovieCategory.POPULAR -> {
                popularMoviesResult = moviesResult
            }
            MovieCategory.TOP_RATED -> {
                topRatedMoviesResult = moviesResult
            }
            MovieCategory.UPCOMING -> {
                upcomingMoviesResult = moviesResult
            }
        }
        return moviesResult
    }
}