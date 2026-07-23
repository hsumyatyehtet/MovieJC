package com.hmyh.moviejc.movieui.navagation

enum class MovieScreens {
    HomeMovie,
    DetailMovie,
    SearchMovie;
    companion object{
        fun fromRoute(route: String?): MovieScreens
        = when(route?.substringBefore("/")){
            HomeMovie.name -> HomeMovie
            DetailMovie.name -> DetailMovie
            SearchMovie.name -> SearchMovie
            null -> HomeMovie
            else -> throw IllegalArgumentException("Invalid route: $route")
        }
    }
}