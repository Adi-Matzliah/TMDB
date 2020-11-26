package com.exercise.firstdigitalbank.tmdb.data.mapper

import com.exercise.firstdigitalbank.tmdb.data.model.*
import com.exercise.firstdigitalbank.tmdb.network.response.*

sealed class NetworkMapper {
    object MovieMapper: IEntityMapper<ResultsItem, Movie> {
        override fun mapFromEntity(entity: ResultsItem) =
            Movie(
                id = entity.id,
                name = entity.title ?: UNKNOWN_FIELD,
                backdropPath = entity.backdropPath ?: FALLBACK_URL_PATH,
                posterPath = entity.posterPath ?: FALLBACK_URL_PATH,
                category = entity.category
            )

        override fun mapToEntity(domainModel: Movie)=

            ResultsItem(
                id = domainModel.id,
                title = domainModel.name,
                backdropPath = domainModel.backdropPath,
                posterPath = domainModel.posterPath,
                category = domainModel.category!!
            )
    }

    object MovieDescriptionMapper: IEntityMapper<GetFullMovieDetailsResponse, MovieDescription> {
        override fun mapFromEntity(entity: GetFullMovieDetailsResponse) =
            MovieDescription(
                id = entity.id,
                name = entity.title ?: UNKNOWN_FIELD,
                backdropPath = entity.backdropPath ?: FALLBACK_URL_PATH,
                posterPath = entity.posterPath ?: FALLBACK_URL_PATH,
                budget = entity.budget,
                releaseDate = entity.releaseDate,
                runtime = entity.runtime,
                rating = entity.voteAverage,
                status = entity.status,
                overview = entity.overview,
                videos = entity.videos?.results?.map { Video(it!!.id, it.site) },
                casts = entity.credits?.casts?.map { Cast(it!!.name, it.profilePath ?: FALLBACK_URL_PATH)},
            )


        override fun mapToEntity(domainModel: MovieDescription)=
            GetFullMovieDetailsResponse(
                id = domainModel.id,
                title = domainModel.name,
                backdropPath = domainModel.backdropPath,
                posterPath = domainModel.posterPath,
                budget = domainModel.budget,
                releaseDate = domainModel.releaseDate,
                runtime = domainModel.runtime,
                voteAverage = domainModel.rating,
                status = domainModel.status,
                overview = domainModel.overview,
                videos = Videos(domainModel.videos?.map { VideoItem(id = it.id, site = it.site) }),
                credits = Credits(domainModel.casts?.map { CastItem(name = it.name, profilePath = it.profilePath) })
            )
    }

    companion object {
        const val FALLBACK_URL_PATH = "/fallback.jpg"
        const val UNKNOWN_FIELD = "N/A"
    }
}