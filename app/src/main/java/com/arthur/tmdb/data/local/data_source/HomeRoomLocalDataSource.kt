package com.arthur.tmdb.data.local.data_source

import com.arthur.tmdb.data.local.dao.MovieDao
import com.arthur.tmdb.data.local.entity.MoviePlayingNowEntity
import com.arthur.tmdb.data.local.entity.MoviePopularEntity
import com.arthur.tmdb.data.models.MediaItem
import com.arthur.tmdb.data.repository.HomeLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeRoomLocalDataSource(private val dao: MovieDao) : HomeLocalDataSource {

    override fun getRandomPlayingNowMovies(): Flow<List<MediaItem>> = dao
        .getRandomPlayingNowMovies()
        .map { it.map { entity -> MediaItem(entity) } }

    override fun getRandomPopularMovies(): Flow<List<MediaItem>> = dao
        .getRandomPopularMovies()
        .map { it.map { entity -> MediaItem(entity) } }

    override suspend fun insertMovie(entity: MoviePlayingNowEntity) = dao
        .insertMovie(entity)

    override suspend fun insertMovie(entity: MoviePopularEntity) = dao
        .insertMovie(entity)

}