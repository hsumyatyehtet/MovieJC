package com.hmyh.moviejc.movieui.navagation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hmyh.moviejc.domain.feature.home.model.Movie
import com.hmyh.moviejc.movieui.feature.detail.DetailMovie
import com.hmyh.moviejc.movieui.feature.home.HomeMovie

@Composable
fun MovieNavigation(movieList: List<Movie>){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = MovieScreens.HomeMovie.name){
        composable(MovieScreens.HomeMovie.name) {
            HomeMovie(navController,movieList)
        }
        composable(MovieScreens.DetailMovie.name+"/{movie}",
            arguments = listOf(navArgument(name = "movie"){type = NavType.StringType})
        ) {backStackEntry->
            DetailMovie(navController, backStackEntry.arguments?.getString("movie"))
        }
    }
}