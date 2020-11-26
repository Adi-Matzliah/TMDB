package com.exercise.firstdigitalbank.tmdb.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.exercise.firstdigitalbank.tmdb.data.mapper.NetworkMapper
import com.exercise.firstdigitalbank.tmdb.data.model.MovieCategory
import com.exercise.firstdigitalbank.tmdb.network.TmdbApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class RemoteRepository @Inject constructor(private val api: TmdbApi) {

    private val pagingConfig = PagingConfig(
        pageSize = NETWORK_PAGE_SIZE,
        enablePlaceholders = true
    )

    /**
     * Fetch movies by category, expose them as a stream of data that will emit
     * every time we get more data from the network.
     */

    fun getMoviesByCategory(category: MovieCategory) = Pager(
        config = pagingConfig,
        pagingSourceFactory = { TmdbPagingSource(api, category) }
    ).flow

    suspend fun getMovieDetails(id: Int) =
        withContext(coroutineContext + Dispatchers.IO) {
            NetworkMapper.MovieDescriptionMapper.mapFromEntity(api.getMovieDetails(id))
        }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}


