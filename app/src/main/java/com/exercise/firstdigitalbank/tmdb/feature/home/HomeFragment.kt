package com.exercise.firstdigitalbank.tmdb.feature.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.exercise.firstdigitalbank.tmdb.R
import com.exercise.firstdigitalbank.tmdb.shared.adapter.OnItemClickListener
import com.exercise.firstdigitalbank.tmdb.data.model.Movie
import com.exercise.firstdigitalbank.tmdb.data.model.MovieCategory
import com.exercise.firstdigitalbank.tmdb.feature.movie.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.now_playing_movies_layout.*
import kotlinx.android.synthetic.main.popular_movies_layout.*
import kotlinx.android.synthetic.main.top_rated_movies_layout.*
import kotlinx.android.synthetic.main.upcoming_movies_layout.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), OnItemClickListener<Movie> {
    private val viewModel: MoviesViewModel by viewModels()

    private val nowPlayingMoviesAdapter = MoviesRecyclerViewAdapter(MovieCategory.NOW_PLAYING, this)
    private val popularMoviesAdapter = MoviesRecyclerViewAdapter(MovieCategory.POPULAR, this)
    private val topRatedMoviesAdapter = MoviesRecyclerViewAdapter(MovieCategory.TOP_RATED, this)
    private val upcomingMoviesAdapter = MoviesRecyclerViewAdapter(MovieCategory.UPCOMING, this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initAdapters()
        initClickListeners()
    }

    private fun initClickListeners() {
        tv_full_now_playing_category.setOnClickListener { openCategoryScreen(MovieCategory.NOW_PLAYING) }
        tv_full_popular_category.setOnClickListener { openCategoryScreen(MovieCategory.POPULAR) }
        tv_full_top_rated_category.setOnClickListener { openCategoryScreen(MovieCategory.TOP_RATED) }
        tv_full_upcoming_category.setOnClickListener { openCategoryScreen(MovieCategory.UPCOMING) }
    }

    private fun initAdapters() {
        rv_now_playing_movies.adapter = nowPlayingMoviesAdapter
        rv_popular_movies.adapter = popularMoviesAdapter
        rv_top_rated_movies.adapter = topRatedMoviesAdapter
        rv_upcoming_movies.adapter = upcomingMoviesAdapter
    }

    private fun subscribeObservers() {
        lifecycleScope.launch {
            viewModel.fetchMoviesCategoryFlow(MovieCategory.NOW_PLAYING).collectLatest {
                nowPlayingMoviesAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            viewModel.fetchMoviesCategoryFlow(MovieCategory.POPULAR).collectLatest {
                popularMoviesAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            viewModel.fetchMoviesCategoryFlow(MovieCategory.TOP_RATED).collectLatest {
                topRatedMoviesAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            viewModel.fetchMoviesCategoryFlow(MovieCategory.UPCOMING).collectLatest {
                upcomingMoviesAdapter.submitData(it)
            }
        }

    }

    private fun openCategoryScreen(movieCategory: MovieCategory) {
        val fullMovieCategoryAction =
            HomeFragmentDirections.actionHiltHomeFragmentToHiltFullCategoryFragment(movieCategory)
        findNavController().navigate(fullMovieCategoryAction)
    }

    private fun openMovieDetailsScreen(movieId: Int) {
        val movieDetailsScreenAction =
            HomeFragmentDirections.actionHiltHomeFragmentToHiltMovieDetailsFragment(movieId)
        findNavController().navigate(movieDetailsScreenAction)
    }

    override fun ontItemClick(item: Movie, position: Int) {
        openMovieDetailsScreen(item.id)
    }
}
