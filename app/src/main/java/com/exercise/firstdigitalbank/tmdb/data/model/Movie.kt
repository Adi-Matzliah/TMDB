package com.exercise.firstdigitalbank.tmdb.data.model

import com.exercise.firstdigitalbank.tmdb.shared.adapter.ViewItem

open class Movie (
    val id: Int,
    val name: String,
    val backdropPath: String,
    val posterPath: String,
    var category: MovieCategory? = null
) : ViewItem()