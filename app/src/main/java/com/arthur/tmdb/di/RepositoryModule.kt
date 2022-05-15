package com.arthur.tmdb.di

import android.content.Context
import com.arthur.tmdb.data.local.AppDatabase
import com.arthur.tmdb.data.local.data_source.HomeRetrofitRemoteDataSource
import com.arthur.tmdb.data.local.data_source.HomeRoomLocalDataSource
import com.arthur.tmdb.data.remote.api.MovieApi
import com.arthur.tmdb.data.repository.HomeLocalDataSource
import com.arthur.tmdb.data.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun providesReportRepository(
        db: AppDatabase,
        movieApi: MovieApi
    ): HomeRepository = HomeRepository(
        HomeRoomLocalDataSource(db.movieDao()),
        HomeRetrofitRemoteDataSource(movieApi)
    )

}