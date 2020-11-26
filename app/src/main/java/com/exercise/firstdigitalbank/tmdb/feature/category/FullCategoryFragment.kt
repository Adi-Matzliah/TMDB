package com.exercise.firstdigitalbank.tmdb.feature.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.exercise.firstdigitalbank.tmdb.R
import com.exercise.firstdigitalbank.tmdb.shared.adapter.OnItemClickListener
import com.exercise.firstdigitalbank.tmdb.data.model.Movie
import com.exercise.firstdigitalbank.tmdb.feature.movie.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_full_category.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FullCategoryFragment: Fragment(R.layout.fragment_full_category), OnItemClickListener<Movie> {
    private val viewModel: MoviesViewModel by viewModels()

    private lateinit var adapter: FullCategoryRecyclerViewAdapter
    private val args: FullCategoryFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        subscribeObservers()
    }

    private fun initAdapter() {
        adapter = FullCategoryRecyclerViewAdapter(args.movieCategory, this)
        rv_movie_category.adapter = adapter
    }

    private fun subscribeObservers() {
        lifecycleScope.launch {
            viewModel.fetchMoviesCategoryFlow(args.movieCategory).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun openMovieDetailsScreen(movieId: Int) {
        val movieDetailsScreenAction =
            FullCategoryFragmentDirections.actionHiltFullCategoryFragmentToHiltMovieDetailsFragment(movieId)
        findNavController().navigate(movieDetailsScreenAction)
    }

    override fun ontItemClick(item: Movie, position: Int) {
        openMovieDetailsScreen(item.id)
    }
}
