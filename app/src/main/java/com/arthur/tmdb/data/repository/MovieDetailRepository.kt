package com.arthur.tmdb.data.repository

import com.arthur.tmdb.data.local.entity.MovieDetailEntity
import com.arthur.tmdb.data.models.MovieItem
import com.arthur.tmdb.data.remote.dto.MovieDetailDto
import com.arthur.tmdb.utils.ServiceResult
import com.arthur.tmdb.utils.getDto
import com.arthur.tmdb.utils.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class MovieDetailRepository(
    private val localDS: MovieDetailLocalDataSource,
    private val remoteDS: MovieDetailRemoteDataSource,
) {
    fun getMovieDetail(movieId: Long): Flow<MovieItem?> = localDS.getMovieDetail(movieId)
        .map { movieDetail ->
            if(null == movieDetail){
                val response = remoteDS.getMovieDetail(movieId)
                if(response.succeeded){
                    val mEntity = MovieDetailEntity(response.getDto())
                    localDS.insertMovie(mEntity)
                    return@map MovieItem(mEntity)
                }
            }
            return@map movieDetail
        }
        .catch { e -> e.printStackTrace() }
        .flowOn(Dispatchers.IO)
}

interface MovieDetailLocalDataSource {
    fun getMovieDetail(movieId: Long): Flow<MovieItem?>
    suspend fun insertMovie(entity: MovieDetailEntity)
}

interface MovieDetailRemoteDataSource {
    suspend fun getMovieDetail(movieId: Long): ServiceResult<MovieDetailDto>
}