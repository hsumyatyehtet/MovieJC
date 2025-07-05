package com.hmyh.moviejc.domain.feature.home.repository

import com.hmyh.moviejc.domain.feature.home.model.Movie

interface MovieRepository {

    suspend fun getNowPlayingMovies(apiKey: String): List<Movie>

}