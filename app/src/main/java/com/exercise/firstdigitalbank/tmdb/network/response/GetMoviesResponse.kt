package com.exercise.firstdigitalbank.tmdb.network.response

import com.exercise.firstdigitalbank.tmdb.data.model.MovieCategory
import com.google.gson.annotations.SerializedName

sealed class GetMoviesResponse {
    @SerializedName("page")
    open var page: Int = 0

    @SerializedName("total_pages")
    open var totalPages: Int = 0

    @SerializedName("results")
    open var results: List<ResultsItem> = emptyList()

    @SerializedName("total_results")
    open var totalResults: Int = 0

    data class GetNowPlayingResponse(

        @SerializedName("dates")
        val dates: Dates,

        ) : GetMoviesResponse()

    class GetPopularResponse : GetMoviesResponse()

    class GetTopRatedResponse : GetMoviesResponse()

    data class GetUpcomingResponse(

        @SerializedName("dates")
        val dates: Dates,

        ) : GetMoviesResponse()
}

data class ResultsItem(

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("original_language")
    var originalLanguage: String? = null,

    @SerializedName("original_title")
    var originalTitle: String? = null,

    @SerializedName("video")
    var video: Boolean? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("genre_ids")
    var genreIds: List<Int> = emptyList(),

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("release_date")
    var releaseDate: String? = null,

    @SerializedName("popularity")
    var popularity: Double? = null,

    @SerializedName("vote_average")
    var voteAverage: Double? = null,

    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("adult")
    var adult: Boolean? = null,

    @SerializedName("vote_count")
    var voteCount: Int = 0,

    var category: MovieCategory
)

data class Dates(

    @SerializedName("maximum")
    val maximum: String,

    @SerializedName("minimum")
    val minimum: String
)