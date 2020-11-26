package com.exercise.firstdigitalbank.tmdb.network.response

import com.exercise.firstdigitalbank.tmdb.data.model.MovieCategory
import com.google.gson.annotations.SerializedName

data class GetFullMovieDetailsResponse(

	@SerializedName("original_language")
	val originalLanguage: String? = null,

	@SerializedName("imdb_id")
	val imdbId: String? = null,

	@SerializedName("videos")
	val videos: Videos? = null,

	@SerializedName("video")
	val video: Boolean? = null,

	@SerializedName("title")
	val title: String? = null,

	@SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@SerializedName("revenue")
	val revenue: Int? = null,

	@SerializedName("credits")
	val credits: Credits? = null,

	@SerializedName("genres")
	val genres: List<GenresItem?>? = null,

	@SerializedName("popularity")
	val popularity: Double? = null,

	@SerializedName("production_countries")
	val productionCountries: List<ProductionCountriesItem?>? = null,

	@SerializedName("id")
	val id: Int,

	@SerializedName("vote_count")
	val voteCount: Int? = null,

	@SerializedName("budget")
	val budget: Int,

	@SerializedName("overview")
	val overview: String,

	@SerializedName("original_title")
	val originalTitle: String? = null,

	@SerializedName("runtime")
	val runtime: Int,

	@SerializedName("poster_path")
	val posterPath: String? = null,

	@SerializedName("spoken_languages")
	val spokenLanguages: List<SpokenLanguagesItem?>? = null,

	@SerializedName("production_companies")
	val productionCompanies: List<ProductionCompaniesItem?>? = null,

	@SerializedName("release_date")
	val releaseDate: String,

	@SerializedName("vote_average")
	val voteAverage: Double,

	@SerializedName("belongs_to_collection")
	val belongsToCollection: Any? = null,

	@SerializedName("tagline")
	val tagline: String? = null,

	@SerializedName("adult")
	val adult: Boolean? = null,

	@SerializedName("homepage")
	val homepage: String? = null,

	@SerializedName("status")
	val status: String
)

data class Credits(

	@SerializedName("cast")
	val casts: List<CastItem?>? = null,

	@SerializedName("crew")
	val crew: List<CrewItem?>? = null
)

data class ProductionCountriesItem(

	@SerializedName("iso_3166_1")
	val iso31661: String? = null,

	@SerializedName("name")
	val name: String? = null
)

data class SpokenLanguagesItem(

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("iso_639_1")
	val iso6391: String? = null,

	@SerializedName("english_name")
	val englishName: String? = null
)

data class ProductionCompaniesItem(

	@SerializedName("logo_path")
	val logoPath: Any? = null,

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("id")
	val id: Int? = null,

	@SerializedName("origin_country")
	val originCountry: String? = null
)

data class GenresItem(

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("id")
	val id: Int? = null
)

data class Videos(

	@SerializedName("results")
	val results: List<VideoItem?>? = null
)

data class CastItem(

	@field:SerializedName("cast_id")
	val castId: Int? = null,

	@field:SerializedName("character")
	val character: String? = null,

	@field:SerializedName("gender")
	val gender: Int? = null,

	@field:SerializedName("credit_id")
	val creditId: String? = null,

	@field:SerializedName("known_for_department")
	val knownForDepartment: String? = null,

	@field:SerializedName("original_name")
	val originalName: String? = null,

	@field:SerializedName("popularity")
	val popularity: Double? = null,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("profile_path")
	val profilePath: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("adult")
	val adult: Boolean? = null,

	@field:SerializedName("order")
	val order: Int? = null
)

data class CrewItem(

	@field:SerializedName("gender")
	val gender: Int? = null,

	@field:SerializedName("credit_id")
	val creditId: String? = null,

	@field:SerializedName("known_for_department")
	val knownForDepartment: String? = null,

	@field:SerializedName("original_name")
	val originalName: String? = null,

	@field:SerializedName("popularity")
	val popularity: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("profile_path")
	val profilePath: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("adult")
	val adult: Boolean? = null,

	@field:SerializedName("department")
	val department: String? = null,

	@field:SerializedName("job")
	val job: String? = null
)

data class VideoItem(

	@field:SerializedName("site")
	val site: String,

	@field:SerializedName("size")
	val size: Int? = null,

	@field:SerializedName("iso_3166_1")
	val iso31661: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("iso_639_1")
	val iso6391: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)
