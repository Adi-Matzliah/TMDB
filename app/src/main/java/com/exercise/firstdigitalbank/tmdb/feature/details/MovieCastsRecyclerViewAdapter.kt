package com.exercise.firstdigitalbank.tmdb.feature.details

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.exercise.firstdigitalbank.tmdb.R
import com.exercise.firstdigitalbank.tmdb.core.adapter.GenericViewAdapter
import com.exercise.firstdigitalbank.tmdb.core.adapter.ViewHolderFactory
import com.exercise.firstdigitalbank.tmdb.data.model.Cast
import com.exercise.firstdigitalbank.tmdb.data.model.Video
import com.google.android.material.card.MaterialCardView

class MovieCastsRecyclerViewAdapter(casts: List<Cast>) :
    GenericViewAdapter<Cast>(casts) {
    override fun getLayoutId(position: Int, obj: Cast) = R.layout.movie_cast_item_list

    override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder = ViewHolderFactory.create(view, viewType)

}