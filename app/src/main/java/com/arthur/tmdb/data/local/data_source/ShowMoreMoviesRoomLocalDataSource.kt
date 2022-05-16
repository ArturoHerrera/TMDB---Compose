package com.arthur.tmdb.data.local.data_source

import com.arthur.tmdb.data.local.dao.MovieDao
import com.arthur.tmdb.data.local.entity.MoviePlayingNowEntity
import com.arthur.tmdb.data.models.MediaItem
import com.arthur.tmdb.data.repository.ShowMoreMoviesLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShowMoreMoviesRoomLocalDataSource(
    private val dao: MovieDao
) : ShowMoreMoviesLocalDataSource {
    override fun getPlayingNowMoviesByPage(page: Int): Flow<List<MediaItem>> = dao
        .getPlayingNowMoviesByPage(page)
        .map { it.map { entity -> MediaItem(entity) } }

    override suspend fun insertMovie(entity: MoviePlayingNowEntity) = dao
        .insertMovie(entity)
}