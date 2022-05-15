package com.arthur.tmdb.ui.screens.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthur.tmdb.data.repository.MovieDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository
) : ViewModel() {

    private val vmUiState = MutableStateFlow(MovieDetailUiState())

    val uiState = vmUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        vmUiState.value
    )

    fun getMovieDetail(movieId: Long?){
        movieId?.let{
            viewModelScope.launch {
                movieDetailRepository.getMovieDetail(it).collect { movieDetail ->
                    vmUiState.update { it.copy( movieDetail = movieDetail ) }
                }
            }
        }
    }
}