package com.arthur.tmdb.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthur.tmdb.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val vmUiState = MutableStateFlow(HomeUiState())

    val uiState = vmUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        vmUiState.value
    )

    init {
        viewModelScope.launch {
            homeRepository.getRandomPlayingNowMovies().collect { mediaItemList ->
                vmUiState.update { it.copy( playingNowMovieList = mediaItemList ) }
            }
        }
    }
}