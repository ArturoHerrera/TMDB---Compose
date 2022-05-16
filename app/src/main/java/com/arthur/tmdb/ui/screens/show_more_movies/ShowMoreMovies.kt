package com.arthur.tmdb.ui.screens.show_more_movies

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.tmdb.R
import com.arthur.tmdb.ui.components.DetailMovieComponent
import com.arthur.tmdb.ui.components.GridMoviesComponent
import com.arthur.tmdb.ui.components.MediaHorizontalListComponent
import com.arthur.tmdb.ui.screens.home.HomeViewModel
import com.arthur.tmdb.ui.screens.movie_detail.MovieDetailViewModel
import com.arthur.tmdb.ui.theme.DarknesBlack
import com.arthur.tmdb.ui.theme.SuperWhite
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun ShowMoreMoviesScreen(
    navigateToMovieDetail: (Long) -> Unit,
    popUpToToHome: () -> Unit,
    viewModel: ShowMoreMoviesViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.home_welcome_title),
                        style = MaterialTheme.typography.h6,
                        color = SuperWhite
                    )
                },
                navigationIcon = {
                    IconButton(modifier = Modifier.padding(8.dp), onClick = {}) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_tmdb_short_logo),
                            contentDescription = null
                        )
                    }
                },
                backgroundColor = DarknesBlack
            )
        }
    ) {
        //TODO Detectar si el ultimo item es visible, aumentar el page y hacer la peticion. Por eso el metodo del viewModel esta publico.
        GridMoviesComponent(
            mediaList = uiState.movieList,
            onMediaClick = { mediaId -> navigateToMovieDetail(mediaId) },
            lastItemVisible = { isVisible -> }
        )
    }
}