package com.exercise.firstdigitalbank.tmdb.feature.home

import android.view.View
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
import com.exercise.firstdigitalbank.tmdb.shared.adapter.GenericViewAdapter
import com.exercise.firstdigitalbank.tmdb.shared.adapter.OnItemClickListener

class MovieViewHolder(private val view: View, private val clickListener: OnItemClickListener<Movie>) :
    RecyclerView.ViewHolder(view), GenericViewAdapter.Binder<Movie>, View.OnClickListener {
    private val name: TextView = view.findViewById(R.id.tv_name)
    private val thumbnail: ImageView = view.findViewById(R.id.iv_thumbnail)
    private var moviePreview: Movie? = null

    override fun bind(movie: Movie) {
        this.moviePreview = movie
        moviePreview?.let {
            val resources = itemView.resources
            name.text = it.name
            //thumbnail.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams(thumbnail.width, thumbnail.height))
            Glide.with(thumbnail.context)
                .load("${resources.getString(R.string.tmdb_image_base_url)}${if (it.category == MovieCategory.NOW_PLAYING) it.backdropPath else it.posterPath}")
                .transform(MultiTransformation(CenterCrop(), RoundedCorners(ROUND_CORNER_RADIUS)))
                //.apply(RequestOptions().error(R.drawable.ic_launcher_background))
                .into(thumbnail)
        }
        view.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        clickListener.ontItemClick(moviePreview!!, adapterPosition)
    }

    companion object {
        private const val ROUND_CORNER_RADIUS = 25
    }


}