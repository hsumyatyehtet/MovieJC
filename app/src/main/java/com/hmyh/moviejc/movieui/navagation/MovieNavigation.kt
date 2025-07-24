package com.hmyh.moviejc.movieui.navagation

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hmyh.moviejc.movieui.feature.detail.DetailMovie
import com.hmyh.moviejc.movieui.feature.home.HomeMovieNew

@Composable
fun MovieNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = MovieScreens.HomeMovie.name){
        composable(MovieScreens.HomeMovie.name) {
           // HomeMovie(navController,movieList)
            HomeMovieNew(navController)
        }
        composable(MovieScreens.DetailMovie.name+"/{id}",
            arguments = listOf(navArgument(name = "id"){type = NavType.LongType})
        ) {backStackEntry->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                DetailMovie(navController, backStackEntry.arguments?.getLong("id")?: 0L)
            }
        }
    }
}