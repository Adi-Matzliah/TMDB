package com.exercise.firstdigitalbank.tmdb.feature.details

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.exercise.firstdigitalbank.tmdb.R
import com.exercise.firstdigitalbank.tmdb.shared.adapter.GenericViewAdapter
import com.exercise.firstdigitalbank.tmdb.shared.adapter.ViewHolderFactory
import com.exercise.firstdigitalbank.tmdb.data.model.Video

class MovieVideosRecyclerViewAdapter(videos: List<Video>) :
    GenericViewAdapter<Video>(videos) {
    override fun getLayoutId(position: Int, obj: Video) = R.layout.movie_video_item_list

    override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder = ViewHolderFactory.create(view, viewType)

}