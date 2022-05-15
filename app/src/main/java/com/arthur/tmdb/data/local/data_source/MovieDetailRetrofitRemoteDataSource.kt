package com.arthur.tmdb.data.local.data_source

import com.arthur.tmdb.data.remote.api.MovieApi
import com.arthur.tmdb.data.remote.dto.MovieDetailDto
import com.arthur.tmdb.data.repository.MovieDetailRemoteDataSource
import com.arthur.tmdb.utils.ApiUtils
import com.arthur.tmdb.utils.ServiceResult

class MovieDetailRetrofitRemoteDataSource(
    private val movieApi: MovieApi
) : MovieDetailRemoteDataSource {
    override suspend fun getMovieDetail(movieId: Long): ServiceResult<MovieDetailDto> {
        val response = movieApi.getMovie(movieId)

        return if (response.isSuccessful) {
            ServiceResult.Success(response.body())
        } else {
            ServiceResult.Error(ApiUtils.getStatusCodeErrorMessage(response.code()))
        }
    }
}