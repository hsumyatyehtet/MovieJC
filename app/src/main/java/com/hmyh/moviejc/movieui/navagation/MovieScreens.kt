package com.hmyh.moviejc.movieui.navagation

enum class MovieScreens {
    HomeMovie,
    DetailMovie,
    SearchMovie,
    NowPlayingMovies,
    PopularMovies,
    TopRatedMovies,
    UpcomingMovies;
    companion object{
        fun fromRoute(route: String?): MovieScreens
        = when(route?.substringBefore("/")){
            HomeMovie.name -> HomeMovie
            DetailMovie.name -> DetailMovie
            SearchMovie.name -> SearchMovie
            NowPlayingMovies.name -> NowPlayingMovies
            PopularMovies.name -> PopularMovies
            TopRatedMovies.name -> TopRatedMovies
            UpcomingMovies.name -> UpcomingMovies
            null -> HomeMovie
            else -> throw IllegalArgumentException("Invalid route: $route")
        }
    }
}
