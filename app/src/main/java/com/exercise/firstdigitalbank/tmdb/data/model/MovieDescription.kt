package com.exercise.firstdigitalbank.tmdb.data.model

class MovieDescription (
    id: Int,
    name: String,
    backdropPath: String,
    posterPath: String,
    val budget: Int,
    val releaseDate: String,
    val runtime: Int,
    val rating: Double,
    val status: String,
    val overview: String,
    val videos: List<Video>?,
    val casts: List<Cast>?
) : Movie(id, name, backdropPath, posterPath)
