package com.hmyh.moviejc.movieui.navagation

enum class MovieScreens {
    HomeMovie,
    DetailMovie;
    companion object{
        fun fromRoute(route: String?): MovieScreens
        = when(route?.substringBefore("/")){
            HomeMovie.name -> HomeMovie
            DetailMovie.name -> DetailMovie
            null -> HomeMovie
            else -> throw IllegalArgumentException("Invalid route: $route")
        }
    }
}