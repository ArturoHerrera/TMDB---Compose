package com.arthur.tmdb.data.local.data_source

import com.arthur.tmdb.data.local.dao.MovieDao
import com.arthur.tmdb.data.local.entity.MovieDetailEntity
import com.arthur.tmdb.data.models.MovieItem
import com.arthur.tmdb.data.repository.MovieDetailLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieDetailRoomLocalDataSource(private val dao: MovieDao) : MovieDetailLocalDataSource {
    override fun getMovieDetail(movieId: Long): Flow<MovieItem?> = dao
        .getMovieDetail(movieId).map { it?.let{ entity -> MovieItem(entity) } }

    override suspend fun insertMovie(entity: MovieDetailEntity) =
        dao.insertMovieDetail(entity)
}