package com.exercise.firstdigitalbank.tmdb.network

import com.exercise.firstdigitalbank.tmdb.data.model.MovieCategory
import com.exercise.firstdigitalbank.tmdb.network.response.GetMoviesResponse

object GetMoviesRequestFactory {

    suspend fun create(api: TmdbApi, category: MovieCategory, page: Int): GetMoviesResponse {
        return when (category) {
            MovieCategory.NOW_PLAYING -> api.getNowPlayingMovies(page)
            MovieCategory.POPULAR -> api.getPopularMovies(page)
            MovieCategory.TOP_RATED -> api.getTopRatedMovies(page)
            MovieCategory.UPCOMING -> api.getUpcomingMovies(page)
        }
    }
}