package com.exercise.firstdigitalbank.tmdb.feature.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.exercise.firstdigitalbank.tmdb.R
import com.exercise.firstdigitalbank.tmdb.core.MoviesRecyclerViewAdapter
import com.exercise.firstdigitalbank.tmdb.data.model.MovieCategory
import com.exercise.firstdigitalbank.tmdb.data.model.Video
import com.exercise.firstdigitalbank.tmdb.databinding.DetailsFragmentBinding
import com.exercise.firstdigitalbank.tmdb.feature.movie.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_full_category.*
import kotlinx.android.synthetic.main.fragment_full_category.rv_movie_category
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MovieDetailsFragment: Fragment() {
    private val viewModel: MovieDetailsViewModel by viewModels()

    private lateinit var videoAdapter: MovieVideosRecyclerViewAdapter
    private lateinit var castAdapter: MovieCastsRecyclerViewAdapter

    private lateinit var binding: DetailsFragmentBinding

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        setupToolbar()

        initAdapter()
        initDataBinding()
        subscribeObservers()
        viewModel.fetchMovieDetails(args.movieId)
        return binding.root
    }

    private fun setupToolbar() {
        activity?.setActionBar(binding.toolbar)
        //activity?.actionBar?.title = "title"
    }

    private fun initDataBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
    }

    private fun initAdapter() {
        videoAdapter = MovieVideosRecyclerViewAdapter(emptyList())
        castAdapter = MovieCastsRecyclerViewAdapter(emptyList())
        binding.rvVideos.adapter = videoAdapter
        binding.rvCasts.adapter = castAdapter
    }

    private fun subscribeObservers() {
        viewModel.movieDetails.observe(viewLifecycleOwner) {
            it.videos?.let { videos ->
                videoAdapter.setItems(videos)
            }
        }

        viewModel.movieDetails.observe(viewLifecycleOwner) {
            it.casts?.let { casts ->
                castAdapter.setItems(casts)
            }
        }
    }
}
