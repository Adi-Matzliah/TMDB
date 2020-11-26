package com.exercise.firstdigitalbank.tmdb.shared.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class PagedListViewAdapter<T>(private val listener: OnItemClickListener<T>, DIFF_COMPARATOR: DiffUtil.ItemCallback<T>) : PagingDataAdapter<T, RecyclerView.ViewHolder>(DIFF_COMPARATOR)
where T: ViewItem {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = getViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(viewType, parent, false)
            , viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as GenericViewAdapter.Binder<ViewItem>).bind(getItem(position)!!)
        }
    }

    override fun getItemViewType(position: Int): Int =
        getLayoutId(position, getItem(position))

    protected abstract fun getLayoutId(position: Int, obj: ViewItem?): Int

    protected abstract fun getViewHolder(view: View, viewType: Int):RecyclerView.ViewHolder
}