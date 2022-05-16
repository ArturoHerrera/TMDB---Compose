package com.arthur.tmdb.data.local.data_source

import com.arthur.tmdb.data.remote.api.MovieApi
import com.arthur.tmdb.data.remote.dto.MovieNowPlayingDto
import com.arthur.tmdb.data.repository.ShowMoreMoviesRemoteDataSource
import com.arthur.tmdb.utils.ApiUtils
import com.arthur.tmdb.utils.ServiceResult

class ShowMoreMoviesRetrofitRemoteDataSource(
    private val movieApi: MovieApi
) : ShowMoreMoviesRemoteDataSource {
    override suspend fun getPlayingNowMoviesByPage(page: Int): ServiceResult<MovieNowPlayingDto> {
        val response = movieApi.getPlayingNowMovies(page)

        return if (response.isSuccessful) {
            ServiceResult.Success(response.body())
        } else {
            ServiceResult.Error(ApiUtils.getStatusCodeErrorMessage(response.code()))
        }
    }
}