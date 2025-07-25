package com.hmyh.moviejc.domain.feature.home.repository

import com.hmyh.moviejc.domain.feature.home.model.NowPlayingMovieVO
import com.hmyh.moviejc.domain.feature.home.model.PopularMovieVO

interface MovieRepository {

    suspend fun getNowPlayingMovies(apiKey: String): List<NowPlayingMovieVO>

    suspend fun getPopularMovieList(apiKey: String): List<PopularMovieVO>

}