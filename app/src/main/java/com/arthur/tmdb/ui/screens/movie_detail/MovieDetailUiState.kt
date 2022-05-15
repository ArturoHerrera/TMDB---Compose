package com.arthur.tmdb.ui.screens.movie_detail

import com.arthur.tmdb.data.models.MovieItem

data class MovieDetailUiState(
    val movieDetail: MovieItem? = null,
    val loading: Boolean = true
)