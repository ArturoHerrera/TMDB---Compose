package com.arthur.tmdb.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arthur.tmdb.ui.screens.home.HomeScreen
import com.arthur.tmdb.ui.screens.movie_detail.MovieDetailScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

object Destinations {
    const val HOME_ROUTE = "home"
    const val MOST_POPULAR_MOVIES_ROUTE = "most_popular_movies"
    const val PLAYING_NOW_MOVIES_ROUTE = "playing_now_movies"
    const val MOVIE_DETAIL = "movie_detail"
    const val TV_SHOW_DETAIL = "tv_show_Detail"


    //Args keys
    const val MOVIE_ID = "movie_id"
    const val TV_SHOW_ID = "tv_show_id"
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
                navigateToMostPopularMoviesScreen = actions.navigateToMostPopularMoviesScreen,
                navigateToPlayingNowMoviesScreen = actions.navigateToPlayingNowMoviesScreen,
            )
        }
        composable(route = Destinations.MOVIE_DETAIL + "/{${Destinations.MOVIE_ID}}",
            arguments = listOf(
                navArgument(Destinations.MOVIE_ID) { type = NavType.LongType }
            )) { backStackEntry ->
            MovieDetailScreen(
                movieId = backStackEntry.arguments?.getLong(Destinations.MOVIE_ID),
                onBack = actions.popUpToToHome,
            )
        }
    }
}

class MainActions(navController: NavHostController) {
    val navigateToMovieDetail: (Long) -> Unit = { movieId ->
        navController.navigate(Destinations.MOVIE_DETAIL + "/$movieId")
    }
    val navigateToMostPopularMoviesScreen: () -> Unit = {
        navController.navigate(Destinations.MOST_POPULAR_MOVIES_ROUTE )
    }
    val navigateToPlayingNowMoviesScreen: () -> Unit = {
        navController.navigate(Destinations.PLAYING_NOW_MOVIES_ROUTE )
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
