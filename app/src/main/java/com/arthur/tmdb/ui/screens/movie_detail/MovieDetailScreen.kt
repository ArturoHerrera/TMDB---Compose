package com.arthur.tmdb.ui.screens.movie_detail

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.tmdb.ui.components.DetailMovieComponent
import com.arthur.tmdb.ui.components.VideoPlayer
import com.arthur.tmdb.ui.theme.DarknesBlack
import com.arthur.tmdb.ui.theme.SuperWhite
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun MovieDetailScreen(
    movieId: Long?,
    onBack: () -> Unit,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.getMovieDetail(movieId)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        uiState.movieDetail?.title ?: "",
                        style = MaterialTheme.typography.h6,
                        color = SuperWhite
                    )
                },
                navigationIcon = {
                    IconButton(modifier = Modifier.padding(8.dp), onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = SuperWhite
                        )
                    }
                },
                backgroundColor = DarknesBlack
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarknesBlack)
                .verticalScroll(rememberScrollState())
        ) {
            uiState.movieDetail?.let { safeMovieItem ->
                DetailMovieComponent(safeMovieItem)

                //TODO No funciona aun. Falta investigar mas.
                //VideoPlayer()
            } ?: Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (uiState.loading) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = MaterialTheme.colors.secondary
                    )
                }
            }
        }
    }
}

