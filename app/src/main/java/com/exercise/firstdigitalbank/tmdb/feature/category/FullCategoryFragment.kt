package com.exercise.firstdigitalbank.tmdb.feature.category

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.exercise.firstdigitalbank.tmdb.R
import com.exercise.firstdigitalbank.tmdb.data.model.Movie
import com.exercise.firstdigitalbank.tmdb.feature.movie.MoviesViewModel
import com.exercise.firstdigitalbank.tmdb.shared.adapter.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_full_category.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FullCategoryFragment : Fragment(R.layout.fragment_full_category), OnItemClickListener<Movie> {
    private val viewModel: MoviesViewModel by viewModels()

    private lateinit var adapter: FullCategoryRecyclerViewAdapter
    private val args: FullCategoryFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        subscribeObservers()
        setupActionBar()
    }

    private fun setupActionBar() {
        activity?.title =
            getString(R.string.title_category_name, args.movieCategory.value)
    }

    private fun initAdapter() {
        adapter = FullCategoryRecyclerViewAdapter(args.movieCategory, this)
        rv_movie_category.adapter = adapter
        adapter.addLoadStateListener(loadPagingSourceState)
    }

    private fun subscribeObservers() {
        lifecycleScope.launch {
            viewModel.fetchMoviesCategoryFlow(args.movieCategory).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun openMovieDetailsScreen(movie: Movie) {
        val movieDetailsScreenAction =
            FullCategoryFragmentDirections.actionHiltFullCategoryFragmentToHiltMovieDetailsFragment(
                movie.id,
                movie.name
            )
        findNavController().navigate(movieDetailsScreenAction)
    }

    override fun ontItemClick(item: Movie, position: Int) = openMovieDetailsScreen(item)

    override fun onDestroyView() {
        super.onDestroyView()
        rv_movie_category.adapter = null
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
