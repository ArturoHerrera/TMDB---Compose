package com.arthur.tmdb.ui.screens.show_more_movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthur.tmdb.data.repository.HomeRepository
import com.arthur.tmdb.data.repository.ShowMoreMoviesRepository
import com.arthur.tmdb.ui.screens.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class ShowMoreMoviesViewModel @Inject constructor(
    private val showMoreMoviesRepository: ShowMoreMoviesRepository
) : ViewModel() {
    private val vmUiState = MutableStateFlow(ShowMoreMoviesUiState())

    val uiState = vmUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        vmUiState.value
    )

    init {
        getPlayingNowMoviesByPage(1)
    }

    fun getPlayingNowMoviesByPage(page: Int){
        viewModelScope.launch {
            showMoreMoviesRepository.getPlayingNowMovies(page).collect { mediaItemList ->
                //TODO Actualizar el valor del movieList del uiState de forma que se AÑADAN las nuevas peliculas y no se reasigne.
                vmUiState.update { it.copy( movieList = mediaItemList , page = vmUiState.value.page + 1) }
            }
        }
    }

}
