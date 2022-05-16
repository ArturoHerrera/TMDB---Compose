package com.arthur.tmdb.di

import com.arthur.tmdb.data.local.AppDatabase
import com.arthur.tmdb.data.local.data_source.*
import com.arthur.tmdb.data.remote.api.MovieApi
import com.arthur.tmdb.data.repository.HomeRepository
import com.arthur.tmdb.data.repository.MovieDetailRepository
import com.arthur.tmdb.data.repository.ShowMoreMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
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

    @ViewModelScoped
    @Provides
    fun providesMovieDetailRepository(
        db: AppDatabase,
        movieApi: MovieApi
    ): MovieDetailRepository = MovieDetailRepository(
        MovieDetailRoomLocalDataSource(db.movieDao()),
        MovieDetailRetrofitRemoteDataSource(movieApi)
    )

    @ViewModelScoped
    @Provides
    fun providesShowMoreMoviesRepository(
        db: AppDatabase,
        movieApi: MovieApi
    ): ShowMoreMoviesRepository = ShowMoreMoviesRepository(
        ShowMoreMoviesRoomLocalDataSource(db.movieDao()),
        ShowMoreMoviesRetrofitRemoteDataSource(movieApi)
    )
}