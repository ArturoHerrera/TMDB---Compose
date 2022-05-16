package com.arthur.tmdb.data.models

import com.arthur.tmdb.BuildConfig
import com.arthur.tmdb.data.local.entity.MovieDetailEntity

data class MovieItem(
    val id: Long,
    val title: String,
    val overview: String,
    val video: Boolean,
    val posterPath: String,
    val voteAverage: Float,
    val releaseDate: String
){
    constructor(entity: MovieDetailEntity):this(
        id = entity.id,
        title = entity.title,
        video = entity.video,
        posterPath = entity.posterPath,
        voteAverage = entity.voteAverage,
        releaseDate = entity.releaseDate,
        overview = entity.overview,
    )

    fun getImageUrl() : String = "${BuildConfig.base_image_url}$posterPath"

}