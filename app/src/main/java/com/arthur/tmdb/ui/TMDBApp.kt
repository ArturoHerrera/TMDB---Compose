package com.arthur.tmdb.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.arthur.tmdb.ui.theme.TmdbcomposeTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun TMDBApp() {
    TmdbcomposeTheme {
        val navController = rememberNavController()

        TMDBNavGraph(
            navController = navController
        )
    }
}