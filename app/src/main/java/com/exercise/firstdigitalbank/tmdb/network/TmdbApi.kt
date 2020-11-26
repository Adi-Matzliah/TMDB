package com.exercise.firstdigitalbank.tmdb.network

import com.exercise.firstdigitalbank.tmdb.data.TMDB_STARTING_PAGE_INDEX
import com.exercise.firstdigitalbank.tmdb.network.response.GetFullMovieDetailsResponse
import com.exercise.firstdigitalbank.tmdb.network.response.GetMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int = TMDB_STARTING_PAGE_INDEX): GetMoviesResponse.GetNowPlayingResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int = TMDB_STARTING_PAGE_INDEX): GetMoviesResponse.GetPopularResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int = TMDB_STARTING_PAGE_INDEX): GetMoviesResponse.GetTopRatedResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int = TMDB_STARTING_PAGE_INDEX): GetMoviesResponse.GetUpcomingResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id: Int, @Query("append_to_response") appendToResponse: String = "movies,credits"): GetFullMovieDetailsResponse

}

