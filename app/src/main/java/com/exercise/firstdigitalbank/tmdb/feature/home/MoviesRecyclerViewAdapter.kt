package com.exercise.firstdigitalbank.tmdb.feature.home

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.exercise.firstdigitalbank.tmdb.R
import com.exercise.firstdigitalbank.tmdb.data.model.Movie
import com.exercise.firstdigitalbank.tmdb.data.model.MovieCategory
import com.exercise.firstdigitalbank.tmdb.shared.adapter.OnItemClickListener
import com.exercise.firstdigitalbank.tmdb.shared.adapter.PagedListViewAdapter
import com.exercise.firstdigitalbank.tmdb.shared.adapter.ViewHolderFactory
import com.exercise.firstdigitalbank.tmdb.shared.adapter.ViewItem

class MoviesRecyclerViewAdapter(
    private val category: MovieCategory,
    private val listener: OnItemClickListener<Movie>
) : PagedListViewAdapter<Movie>(listener, MOVIE_COMPARATOR) {

    override fun getLayoutId(position: Int, obj: ViewItem?) =
        when (category) {
            MovieCategory.NOW_PLAYING -> R.layout.movie_preview_horizontal_item_list
            MovieCategory.POPULAR,
            MovieCategory.TOP_RATED,
            MovieCategory.UPCOMING -> R.layout.movie_preview_vertical_item_list
        }

    override fun getViewHolder(view: View, viewType: Int) =
        ViewHolderFactory.create(view, viewType, listener)


    companion object {
        public val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem == newItem
        }
    }

}