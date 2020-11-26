package com.exercise.firstdigitalbank.tmdb.feature.category

import android.view.View
import com.exercise.firstdigitalbank.tmdb.R
import com.exercise.firstdigitalbank.tmdb.feature.home.MoviesRecyclerViewAdapter.Companion.MOVIE_COMPARATOR
import com.exercise.firstdigitalbank.tmdb.shared.adapter.OnItemClickListener
import com.exercise.firstdigitalbank.tmdb.shared.adapter.PagedListViewAdapter
import com.exercise.firstdigitalbank.tmdb.shared.adapter.ViewHolderFactory
import com.exercise.firstdigitalbank.tmdb.shared.adapter.ViewItem
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