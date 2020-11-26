package com.exercise.firstdigitalbank.tmdb.core.adapter

interface OnItemClickListener<T> {
        // TODO: Update argument type and name
    fun ontItemClick(item: T, position: Int)
    fun onLongItemClick(item: T?, position: Int) = Unit
}