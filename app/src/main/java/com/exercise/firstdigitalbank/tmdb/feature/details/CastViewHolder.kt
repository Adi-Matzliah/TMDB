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
import com.exercise.firstdigitalbank.tmdb.data.model.Cast
import kotlinx.android.synthetic.main.movie_cast_item_list.view.*

class CastViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view), GenericViewAdapter.Binder<Cast> {
    private val thumbnail: ImageView = view.findViewById(R.id.iv_thumbnail)
    private lateinit var movieCast: Cast


    override fun bind(cast: Cast) {
        this.movieCast = cast
        val resources = itemView.resources
        view.tv_name.text = cast.name
        Glide.with(thumbnail.context)
            .load("${resources.getString(R.string.tmdb_image_base_url)}${cast.profilePath}")
            .transform(MultiTransformation(CenterCrop(), RoundedCorners(ROUND_CORNER_RADIUS)))
            //.apply(RequestOptions().error(R.drawable.ic_launcher_background))
            .into(thumbnail)
    }

    companion object {
        private const val ROUND_CORNER_RADIUS = 25
    }


}