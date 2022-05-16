package com.arthur.tmdb.ui.screens.home

import com.arthur.tmdb.data.models.MediaItem

data class HomeUiState(
    val playingNowMovieList: List<MediaItem> = listOf(),
    val popularMovieList: List<MediaItem> = listOf()
)