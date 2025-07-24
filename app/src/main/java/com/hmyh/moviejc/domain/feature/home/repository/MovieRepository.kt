package com.hmyh.moviejc.domain.feature.home.repository

import com.hmyh.moviejc.domain.feature.home.model.MovieVO

interface MovieRepository {

    suspend fun getNowPlayingMovies(apiKey: String): List<MovieVO>

}