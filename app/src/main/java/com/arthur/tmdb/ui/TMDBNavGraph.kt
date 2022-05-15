package com.arthur.tmdb.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arthur.tmdb.ui.screens.home.HomeScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

object Destinations {
    const val HOME_ROUTE = "home"
    const val MOVIE_DETAIL = "movie_detail"
    const val TV_SHOW_DETAIL = "tv_show_Detail"
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun TMDBNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.HOME_ROUTE
) {
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destinations.HOME_ROUTE) {
            HomeScreen(
                navigateToMovieDetail = actions.navigateToMovieDetail,
            )
        }
    }
}

class MainActions(navController: NavHostController) {
    val navigateToMovieDetail: () -> Unit = {
        navController.navigate(Destinations.MOVIE_DETAIL) {
            popUpTo(Destinations.HOME_ROUTE) { inclusive = true }
        }
    }
    val popUpToToHome: () -> Unit = {
        navController.navigate(Destinations.HOME_ROUTE) {
            popUpTo(Destinations.HOME_ROUTE) { inclusive = false }
        }
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}
