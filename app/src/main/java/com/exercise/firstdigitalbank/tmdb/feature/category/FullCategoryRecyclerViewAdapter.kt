package com.exercise.firstdigitalbank.tmdb.feature.category

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.exercise.firstdigitalbank.tmdb.R
import com.exercise.firstdigitalbank.tmdb.core.MoviesRecyclerViewAdapter
import com.exercise.firstdigitalbank.tmdb.core.MoviesRecyclerViewAdapter.Companion.MOVIE_COMPARATOR
import com.exercise.firstdigitalbank.tmdb.core.adapter.OnItemClickListener
import com.exercise.firstdigitalbank.tmdb.core.adapter.PagedListViewAdapter
import com.exercise.firstdigitalbank.tmdb.core.adapter.ViewHolderFactory
import com.exercise.firstdigitalbank.tmdb.core.adapter.ViewItem
import com.exercise.firstdigitalbank.tmdb.data.model.Movie
import com.exercise.firstdigitalbank.tmdb.data.model.MovieCategory

class FullCategoryRecyclerViewAdapter(
    private val category: MovieCategory,
    private val listener: OnItemClickListener<Movie>
) : PagedListViewAdapter<Movie>(listener, MOVIE_COMPARATOR) {

    override fun getLayoutId(position: Int, obj: ViewItem?) = R.layout.full_movie_category_vertical_item_list

    override fun getViewHolder(view: View, viewType: Int) =
        ViewHolderFactory.create(view, viewType, listener)
}