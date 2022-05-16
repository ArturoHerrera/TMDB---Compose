package com.arthur.tmdb.data.remote.api

import com.arthur.tmdb.data.remote.dto.MovieDetailDto
import com.arthur.tmdb.data.remote.dto.MovieNowPlayingDto
import com.arthur.tmdb.data.remote.dto.MoviePopularDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{movieId}")
    suspend fun getMovie(
        @Path("movieId") id: Long
    ): Response<MovieDetailDto>

    @GET("movie/now_playing")
    suspend fun getPlayingNowMovies(
        @Query("page") page: Int
    ): Response<MovieNowPlayingDto>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): Response<MoviePopularDto>

}