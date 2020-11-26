package com.exercise.firstdigitalbank.tmdb.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.exercise.firstdigitalbank.tmdb.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    private val appBarConfiguration by lazy { AppBarConfiguration(navController.graph) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBarAppBar()
    }

    private fun setupActionBarAppBar() {
        // Get a support ActionBar corresponding to this toolbar and enable the Up button

        setupActionBarWithNavController(navController, appBarConfiguration)
        actionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
                R.id.hilt_homeFragment -> getString(R.string.app_name)
                else -> getString(R.string.app_name)

            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return super.onSupportNavigateUp()
    }
}