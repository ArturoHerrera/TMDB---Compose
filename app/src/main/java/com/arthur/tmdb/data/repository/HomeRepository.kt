package com.arthur.tmdb.data.repository

import com.arthur.tmdb.data.local.entity.MoviePlayingNowEntity
import com.arthur.tmdb.data.models.MediaItem
import com.arthur.tmdb.data.remote.dto.MovieNowPlayingDto
import com.arthur.tmdb.utils.ServiceResult
import com.arthur.tmdb.utils.getDto
import com.arthur.tmdb.utils.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class HomeRepository(
    private val localDS: HomeLocalDataSource,
    private val remoteDS: HomeRemoteDataSource,
) {

    fun getRandomPlayingNowMovies(): Flow<List<MediaItem>> = localDS.getRandomPlayingNowMovies()
        .map { entityList ->
            entityList.ifEmpty {
                val response = remoteDS.getRandomPlayingNowMovies()
                if (response.succeeded) {
                    response.getDto().results?.map { result ->
                        MoviePlayingNowEntity(response.getDto(), result)
                    }?.map {
                        localDS.insertPlayingNowMovies(it)
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

interface HomeLocalDataSource {
    fun getRandomPlayingNowMovies(): Flow<List<MediaItem>>
    suspend fun insertPlayingNowMovies(entity: MoviePlayingNowEntity)
}

interface HomeRemoteDataSource {
    suspend fun getRandomPlayingNowMovies(): ServiceResult<MovieNowPlayingDto>
}