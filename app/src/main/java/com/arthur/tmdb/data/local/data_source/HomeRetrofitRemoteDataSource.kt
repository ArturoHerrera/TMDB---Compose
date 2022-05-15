package com.arthur.tmdb.data.local.data_source

import com.arthur.tmdb.data.remote.api.MovieApi
import com.arthur.tmdb.data.remote.dto.MovieNowPlayingDto
import com.arthur.tmdb.data.remote.dto.MoviePopularDto
import com.arthur.tmdb.data.repository.HomeRemoteDataSource
import com.arthur.tmdb.utils.ApiUtils
import com.arthur.tmdb.utils.ServiceResult

class HomeRetrofitRemoteDataSource(
    private val movieApi: MovieApi
) : HomeRemoteDataSource {

    override suspend fun getRandomPlayingNowMovies(): ServiceResult<MovieNowPlayingDto> {
        val response = movieApi.getPlayingNowMovies((1..65).random())

        return if (response.isSuccessful) {
            ServiceResult.Success(response.body())
        } else {
            ServiceResult.Error(ApiUtils.getStatusCodeErrorMessage(response.code()))
        }
    }

    override suspend fun getRandomPopularMovies(): ServiceResult<MoviePopularDto> {
        val response = movieApi.getPopularMovies((1..1000).random())

        return if (response.isSuccessful) {
            ServiceResult.Success(response.body())
        } else {
            ServiceResult.Error(ApiUtils.getStatusCodeErrorMessage(response.code()))
        }
    }

}