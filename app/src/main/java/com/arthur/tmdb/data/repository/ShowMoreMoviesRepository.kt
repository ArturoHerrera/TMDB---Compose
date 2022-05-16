package com.arthur.tmdb.data.repository

import com.arthur.tmdb.data.local.entity.MoviePlayingNowEntity
import com.arthur.tmdb.data.models.MediaItem
import com.arthur.tmdb.data.remote.dto.MovieNowPlayingDto
import com.arthur.tmdb.utils.ServiceResult
import com.arthur.tmdb.utils.getDto
import com.arthur.tmdb.utils.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class ShowMoreMoviesRepository(
    private val localDS: ShowMoreMoviesLocalDataSource,
    private val remoteDS: ShowMoreMoviesRemoteDataSource,
) {
    fun getPlayingNowMovies(page: Int): Flow<List<MediaItem>> = localDS.getPlayingNowMoviesByPage(page)
        .map { entityList ->
            entityList.ifEmpty {
                val response = remoteDS.getPlayingNowMoviesByPage(page)
                if (response.succeeded) {
                    response.getDto().results?.map { result ->
                        MoviePlayingNowEntity(response.getDto(), result)
                    }?.map {
                        localDS.insertMovie(it)
                        MediaItem(it)
                    }
                } else {
                    emptyList()
                }
            }
            entityList
        }
        .catch { e -> e.printStackTrace() }
        .flowOn(Dispatchers.IO)
}

interface ShowMoreMoviesLocalDataSource {
    fun getPlayingNowMoviesByPage(page: Int): Flow<List<MediaItem>>
    suspend fun insertMovie(entity: MoviePlayingNowEntity)
}

interface ShowMoreMoviesRemoteDataSource {
    suspend fun getPlayingNowMoviesByPage(page: Int): ServiceResult<MovieNowPlayingDto>
}