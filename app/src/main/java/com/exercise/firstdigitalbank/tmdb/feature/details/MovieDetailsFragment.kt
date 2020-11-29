package com.exercise.firstdigitalbank.tmdb.feature.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.exercise.firstdigitalbank.tmdb.databinding.DetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
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
        binding = DetailsFragmentBinding.inflate(inflater)
        setupActionBar()
        initAdapter()
        initDataBinding()
        subscribeObservers()
        viewModel.fetchMovieDetails(args.movieId)
        return binding.root
    }

    private fun setupActionBar() {
        setupWithNavController(
            binding.toolbar,
            findNavController(),
            AppBarConfiguration(findNavController().graph)
        )
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
            it.casts?.let { casts ->
                castAdapter.setItems(casts)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvVideos.adapter = null
        binding.rvCasts.adapter = null
    }
}
