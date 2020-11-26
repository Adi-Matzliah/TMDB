package com.exercise.firstdigitalbank.tmdb.data.model

open class Movie (
    val id: Int,
    val name: String,
    val backdropPath: String,
    val posterPath: String,
    var category: MovieCategory? = null
)