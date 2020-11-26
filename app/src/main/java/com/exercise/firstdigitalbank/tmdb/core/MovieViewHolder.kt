package com.exercise.firstdigitalbank.tmdb.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.exercise.firstdigitalbank.tmdb.R
import com.exercise.firstdigitalbank.tmdb.data.model.Movie
import com.exercise.firstdigitalbank.tmdb.data.model.MovieCategory

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.tv_name)
    private val thumbnail: ImageView = view.findViewById(R.id.iv_thumbnail)
    private var moviePreview: Movie? = null

/*    init {
        view.setOnClickListener {
            photo?.previewURL?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view.context.startActivity(intent)
            }
        }
    }*/

    fun bind(moviePreview: Movie?) {
        this.moviePreview = moviePreview
        moviePreview?.let {
            val resources = itemView.resources
            name.text = it.name
            //thumbnail.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams(thumbnail.width, thumbnail.height))
            Glide.with(thumbnail.context)
                .load("${resources.getString(R.string.tmdb_image_base_url)}${if (moviePreview.category == MovieCategory.NOW_PLAYING) moviePreview.backdropPath else moviePreview.posterPath}")
                .transform(MultiTransformation(CenterCrop(), RoundedCorners(ROUND_CORNER_RADIUS)))
                //.apply(RequestOptions().error(R.drawable.ic_launcher_background))
                .into(thumbnail)
        }
    }

    companion object {

        private const val ROUND_CORNER_RADIUS = 25

        fun create(parent: ViewGroup, category: MovieCategory): MovieViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(if (category == MovieCategory.NOW_PLAYING) R.layout.movie_preview_horizontal_item_list else R.layout.movie_preview_vertical_item_list, parent, false)
            return MovieViewHolder(view/*, listener*/)
        }
    }
}