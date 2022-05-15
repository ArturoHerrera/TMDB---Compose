package com.arthur.tmdb.ui.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.tmdb.R
import com.arthur.tmdb.ui.components.MediaHorizontalListComponent
import com.arthur.tmdb.ui.theme.DarknesBlack
import com.arthur.tmdb.ui.theme.SuperWhite
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun HomeScreen(
    navigateToMovieDetail: (Long) -> Unit,
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
                        "Welcome :D",
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
                sectionTitle = "Playing Now Movies",
                mediaList = uiState.playingNowMovieList
            ) { movieId -> navigateToMovieDetail(movieId) }

            MediaHorizontalListComponent(
                sectionTitle = "Most Popular Movies",
                mediaList = uiState.popularMovieList
            ) { movieId -> navigateToMovieDetail(movieId) }



            //TODO Remove, only for preview.
            MediaHorizontalListComponent(
                sectionTitle = "Playing Now Movies",
                mediaList = uiState.playingNowMovieList
            ) { movieId -> navigateToMovieDetail(movieId) }

            MediaHorizontalListComponent(
                sectionTitle = "Most Popular Movies",
                mediaList = uiState.popularMovieList
            ) { movieId -> navigateToMovieDetail(movieId) }
        }
    }

}