package com.arthur.tmdb.data.repository

import com.arthur.tmdb.data.local.entity.MoviePlayingNowEntity
import com.arthur.tmdb.data.local.entity.MoviePopularEntity
import com.arthur.tmdb.data.models.MediaItem
import com.arthur.tmdb.data.remote.dto.MovieNowPlayingDto
import com.arthur.tmdb.data.remote.dto.MoviePopularDto
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

    fun getRandomPopularMovies(): Flow<List<MediaItem>> = localDS.getRandomPopularMovies()
        .map { entityList ->
            entityList.ifEmpty {
                val response = remoteDS.getRandomPopularMovies()
                if (response.succeeded) {
                    response.getDto().results?.map { result ->
                        MoviePopularEntity(response.getDto(), result)
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

interface HomeLocalDataSource {
    fun getRandomPlayingNowMovies(): Flow<List<MediaItem>>
    fun getRandomPopularMovies(): Flow<List<MediaItem>>
    suspend fun insertMovie(entity: MoviePlayingNowEntity)
    suspend fun insertMovie(entity: MoviePopularEntity)
}

interface HomeRemoteDataSource {
    suspend fun getRandomPlayingNowMovies(): ServiceResult<MovieNowPlayingDto>
    suspend fun getRandomPopularMovies(): ServiceResult<MoviePopularDto>
}