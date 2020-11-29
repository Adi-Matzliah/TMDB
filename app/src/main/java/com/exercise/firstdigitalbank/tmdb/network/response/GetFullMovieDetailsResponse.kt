package com.exercise.firstdigitalbank.tmdb.network.response

import com.google.gson.annotations.SerializedName


data class GetFullMovieDetailsResponse(

	@SerializedName("original_language")
	var originalLanguage: String? = null,

	@SerializedName("imdb_id")
	var imdbId: String? = null,

	@SerializedName("videos")
	var videos: Videos? = null,

	@SerializedName("video")
	var video: Boolean? = null,

	@SerializedName("title")
	var title: String? = null,

	@SerializedName("backdrop_path")
	var backdropPath: String? = null,

	@SerializedName("revenue")
	var revenue: Int? = null,

	@SerializedName("credits")
	var credits: Credits? = null,

	@SerializedName("genres")
	var genres: List<GenresItem?>? = null,

	@SerializedName("popularity")
	var popularity: Double? = null,

	@SerializedName("production_countries")
	var productionCountries: List<ProductionCountriesItem?>? = null,

	@SerializedName("id")
	var id: Int,

	@SerializedName("vote_count")
	var voteCount: Int? = null,

	@SerializedName("budget")
	var budget: Int,

	@SerializedName("overview")
	var overview: String,

	@SerializedName("original_title")
	var originalTitle: String? = null,

	@SerializedName("runtime")
	var runtime: Int,

	@SerializedName("poster_path")
	var posterPath: String? = null,

	@SerializedName("spoken_languages")
	var spokenLanguages: List<SpokenLanguagesItem?>? = null,

	@SerializedName("production_companies")
	var productionCompanies: List<ProductionCompaniesItem?>? = null,

	@SerializedName("release_date")
	var releaseDate: String,

	@SerializedName("vote_average")
	var voteAverage: Double,

	@SerializedName("belongs_to_collection")
	var belongsToCollection: Any? = null,

	@SerializedName("tagline")
	var tagline: String? = null,

	@SerializedName("adult")
	var adult: Boolean? = null,

	@SerializedName("homepage")
	var homepage: String? = null,

	@SerializedName("status")
	var status: String
)

data class Credits(

	@SerializedName("cast")
	var casts: List<CastItem?>? = null,

	@SerializedName("crew")
	var crew: List<CrewItem?>? = null
)

data class ProductionCountriesItem(

	@SerializedName("iso_3166_1")
	var iso31661: String? = null,

	@SerializedName("name")
	var name: String? = null
)

data class SpokenLanguagesItem(

	@SerializedName("name")
	var name: String? = null,

	@SerializedName("iso_639_1")
	var iso6391: String? = null,

	@SerializedName("english_name")
	var englishName: String? = null
)

data class ProductionCompaniesItem(

	@SerializedName("logo_path")
	var logoPath: Any? = null,

	@SerializedName("name")
	var name: String? = null,

	@SerializedName("id")
	var id: Int? = null,

	@SerializedName("origin_country")
	var originCountry: String? = null
)

data class GenresItem(

	@SerializedName("name")
	var name: String? = null,

	@SerializedName("id")
	var id: Int? = null
)

data class Videos(

	@SerializedName("results")
	var results: List<VideoItem?>? = null
)

data class CastItem(

	@SerializedName("cast_id")
	var castId: Int? = null,

	@SerializedName("character")
	var character: String? = null,

	@SerializedName("gender")
	var gender: Int? = null,

	@SerializedName("credit_id")
	var creditId: String? = null,

	@SerializedName("known_for_department")
	var knownForDepartment: String? = null,

	@SerializedName("original_name")
	var originalName: String? = null,

	@SerializedName("popularity")
	var popularity: Double? = null,

	@SerializedName("name")
	var name: String,

	@SerializedName("profile_path")
	var profilePath: String? = null,

	@SerializedName("id")
	var id: Int? = null,

	@SerializedName("adult")
	var adult: Boolean? = null,

	@SerializedName("order")
	var order: Int? = null
)

data class CrewItem(

	@SerializedName("gender")
	var gender: Int? = null,

	@SerializedName("credit_id")
	var creditId: String? = null,

	@SerializedName("known_for_department")
	var knownForDepartment: String? = null,

	@SerializedName("original_name")
	var originalName: String? = null,

	@SerializedName("popularity")
	var popularity: Double? = null,

	@SerializedName("name")
	var name: String? = null,

	@SerializedName("profile_path")
	var profilePath: String? = null,

	@SerializedName("id")
	var id: Int? = null,

	@SerializedName("adult")
	var adult: Boolean? = null,

	@SerializedName("department")
	var department: String? = null,

	@SerializedName("job")
	var job: String? = null
)

data class VideoItem(

	@SerializedName("site")
	var site: String,

	@SerializedName("size")
	var size: Int? = null,

	@SerializedName("iso_3166_1")
	var iso31661: String? = null,

	@SerializedName("name")
	var name: String? = null,

	@SerializedName("id")
	var id: String,

	@SerializedName("type")
	var type: String? = null,

	@SerializedName("iso_639_1")
	var iso6391: String? = null,

	@SerializedName("key")
	var key: String? = null
)
