package com.arthur.tmdb.data.models

import com.arthur.tmdb.BuildConfig
import com.arthur.tmdb.data.local.entity.MoviePlayingNowEntity

data class MediaItem(
    val page: Int,
    val id: Long,
    val posterUrl: String,
    val title: String
) {
    constructor(entity: MoviePlayingNowEntity) : this(
        page = entity.page,
        id = entity.id,
        posterUrl = entity.posterUrl,
        title = entity.title
    )

    fun getImageUrl() : String = "${BuildConfig.base_image_url}$posterUrl"
}