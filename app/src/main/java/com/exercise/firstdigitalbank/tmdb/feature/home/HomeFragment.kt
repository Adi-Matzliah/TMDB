package com.exercise.firstdigitalbank.tmdb.feature.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.exercise.firstdigitalbank.tmdb.R
import com.exercise.firstdigitalbank.tmdb.shared.adapter.OnItemClickListener
import com.exercise.firstdigitalbank.tmdb.data.model.Movie
import com.exercise.firstdigitalbank.tmdb.data.model.MovieCategory
import com.exercise.firstdigitalbank.tmdb.feature.movie.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
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
        setupActionBar()
    }

    private fun setupActionBar() {
        activity?.title = getString(R.string.app_name)
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

        nowPlayingMoviesAdapter.addLoadStateListener(loadPagingSourceState)
        popularMoviesAdapter.addLoadStateListener(loadPagingSourceState)
        topRatedMoviesAdapter.addLoadStateListener(loadPagingSourceState)
        upcomingMoviesAdapter.addLoadStateListener(loadPagingSourceState)
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

    private fun openMovieDetailsScreen(movie: Movie) {
        val movieDetailsScreenAction =
            HomeFragmentDirections.actionHiltHomeFragmentToHiltMovieDetailsFragment(
                movie.id,
                movie.name
            )
        findNavController().navigate(movieDetailsScreenAction)
    }

    override fun ontItemClick(item: Movie, position: Int) {
        openMovieDetailsScreen(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rv_now_playing_movies.adapter = null
        rv_popular_movies.adapter = null
        rv_top_rated_movies.adapter = null
        rv_upcoming_movies.adapter = null
    }

    private val loadPagingSourceState = fun(loadState: CombinedLoadStates) {
        // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error
            ?: loadState.append as? LoadState.Error
            ?: loadState.prepend as? LoadState.Error
        errorState?.let {
            Toast.makeText(
                context,
                getString(R.string.paging_loading_error_message, errorState.error.message),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
