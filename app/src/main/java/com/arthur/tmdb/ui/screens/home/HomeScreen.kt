package com.arthur.tmdb.ui.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.tmdb.R
import com.arthur.tmdb.ui.components.GridMoviesComponent
import com.arthur.tmdb.ui.components.MediaCardComponent
import com.arthur.tmdb.ui.components.MediaCardGridComponent
import com.arthur.tmdb.ui.components.MediaHorizontalListComponent
import com.arthur.tmdb.ui.theme.DarknesBlack
import com.arthur.tmdb.ui.theme.SuperWhite
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun HomeScreen(
    navigateToMovieDetail: (Long) -> Unit,
    navigateToMostPopularMoviesScreen: () -> Unit,
    navigateToPlayingNowMoviesScreen: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarknesBlack)
                .verticalScroll(rememberScrollState())
        ) {
            MediaHorizontalListComponent(
                sectionTitle = stringResource(id = R.string.playing_now_movies_section),
                mediaList = uiState.playingNowMovieList,
                onMediaClick = { movieId -> navigateToMovieDetail(movieId) },
                onSeeMore = navigateToPlayingNowMoviesScreen
            )

            MediaHorizontalListComponent(
                sectionTitle = stringResource(id = R.string.playing_now_movies_section),
                mediaList = uiState.popularMovieList,
                onMediaClick = { movieId -> navigateToMovieDetail(movieId) },
                onSeeMore = navigateToMostPopularMoviesScreen
            )

        }

        //GridMoviesComponent(uiState.playingNowMovieList){}
    }

}