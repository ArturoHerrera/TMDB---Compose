package com.arthur.tmdb.di

import com.arthur.tmdb.data.remote.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

}