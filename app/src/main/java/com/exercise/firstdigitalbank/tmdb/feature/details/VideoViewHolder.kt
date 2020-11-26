package com.exercise.firstdigitalbank.tmdb.feature.details

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.exercise.firstdigitalbank.tmdb.R
import com.exercise.firstdigitalbank.tmdb.shared.adapter.GenericViewAdapter
import com.exercise.firstdigitalbank.tmdb.data.model.Video

class VideoViewHolder(view: View) :
    RecyclerView.ViewHolder(view), GenericViewAdapter.Binder<Video> {
    private val thumbnail: ImageView = view.findViewById(R.id.iv_thumbnail)
    private lateinit var movieVideo: Video


    override fun bind(video: Video) {
        this.movieVideo = video
        val resources = itemView.resources

        Glide.with(thumbnail.context)
            .load("${resources.getString(R.string.youtube_thumbnail_url)}${video.key}")
            .transform(MultiTransformation(CenterCrop(), RoundedCorners(ROUND_CORNER_RADIUS)))
            //.apply(RequestOptions().error(R.drawable.ic_launcher_background))
            .into(thumbnail)
    }

    companion object {
        private const val ROUND_CORNER_RADIUS = 25
    }


}