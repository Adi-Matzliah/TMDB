package com.exercise.firstdigitalbank.tmdb.data

import androidx.paging.PagingSource
import com.exercise.firstdigitalbank.tmdb.data.mapper.NetworkMapper
import com.exercise.firstdigitalbank.tmdb.data.model.Movie
import com.exercise.firstdigitalbank.tmdb.data.model.MovieCategory
import com.exercise.firstdigitalbank.tmdb.network.GetMoviesRequestFactory
import com.exercise.firstdigitalbank.tmdb.network.TmdbApi
import retrofit2.HttpException
import java.io.IOException

const val TMDB_STARTING_PAGE_INDEX = 1

class TmdbPagingSource(
    private val service: TmdbApi,
    private val movieCategory: MovieCategory
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: TMDB_STARTING_PAGE_INDEX
        return try {
            val response = GetMoviesRequestFactory.create(service, movieCategory, position)
            val movies = response.results.map {
                it.category = movieCategory
                NetworkMapper.MovieMapper.mapFromEntity(it)
            }
            LoadResult.Page(
                data = movies,
                prevKey = if (position == TMDB_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}