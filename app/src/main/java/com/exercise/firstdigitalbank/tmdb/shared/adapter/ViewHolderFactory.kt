package com.exercise.firstdigitalbank.tmdb.shared.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.exercise.firstdigitalbank.tmdb.R
import com.exercise.firstdigitalbank.tmdb.data.model.Movie
import com.exercise.firstdigitalbank.tmdb.feature.details.CastViewHolder
import com.exercise.firstdigitalbank.tmdb.feature.details.VideoViewHolder
import com.exercise.firstdigitalbank.tmdb.feature.home.MovieViewHolder

object ViewHolderFactory {

    fun create(view: View, viewType: Int, clickListener: OnItemClickListener<*>? = null): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.movie_preview_horizontal_item_list,
            R.layout.movie_preview_vertical_item_list,
            R.layout.full_movie_category_vertical_item_list -> MovieViewHolder(view, clickListener as OnItemClickListener<Movie>)
            R.layout.movie_video_item_list -> VideoViewHolder(view)
            R.layout.movie_cast_item_list -> CastViewHolder(view)
            else -> {
                throw NotImplementedError("$viewType layout is not missing")
            }
        }
    }
}