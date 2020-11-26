package com.exercise.firstdigitalbank.tmdb.util.di.module

import android.content.Context
import com.exercise.firstdigitalbank.tmdb.util.ResourcesLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module(
    includes = [
        NetworkModule::class
    ]
)
object AppModule {

    @Singleton
    @Provides
    fun provideResourcesLoader(@ApplicationContext context: Context) = ResourcesLoader(context)
}