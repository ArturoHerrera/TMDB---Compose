package com.arthur.tmdb.ui.screens.show_more_movies

import com.arthur.tmdb.data.models.MediaItem

data class ShowMoreMoviesUiState(
    val movieList: List<MediaItem> = listOf(),
    val page: Int = 1
)