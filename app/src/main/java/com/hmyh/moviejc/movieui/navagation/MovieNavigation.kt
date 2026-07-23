package com.hmyh.moviejc.movieui.navagation

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hmyh.moviejc.movieui.feature.detail.MovieDetailNew
import com.hmyh.moviejc.movieui.feature.home.HomeMovieNew
import com.hmyh.moviejc.movieui.feature.nowplaying.NowPlayingMovies
import com.hmyh.moviejc.movieui.feature.popular.PopularMovies
import com.hmyh.moviejc.movieui.feature.search.SearchMovie
import com.hmyh.moviejc.movieui.feature.toprated.TopRatedMovies
import com.hmyh.moviejc.movieui.feature.upcoming.UpcomingMovies

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
//                DetailMovie(navController, backStackEntry.arguments?.getLong("id")?: 0L)
                MovieDetailNew(navController, backStackEntry.arguments?.getLong("id")?: 0L)
            }
        }
        composable(MovieScreens.SearchMovie.name) {
            SearchMovie(navController)
        }
        composable(MovieScreens.NowPlayingMovies.name) {
            NowPlayingMovies(navController)
        }
        composable(MovieScreens.PopularMovies.name) {
            PopularMovies(navController)
        }
        composable(MovieScreens.TopRatedMovies.name) {
            TopRatedMovies(navController)
        }
        composable(MovieScreens.UpcomingMovies.name) {
            UpcomingMovies(navController)
        }
    }
}
