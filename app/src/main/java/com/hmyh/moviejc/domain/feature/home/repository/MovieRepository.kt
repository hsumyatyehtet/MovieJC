package com.hmyh.moviejc.domain.feature.home.repository

import com.hmyh.moviejc.domain.feature.home.model.NowPlayingMovieVO
import com.hmyh.moviejc.domain.feature.home.model.PopularMovieVO
import com.hmyh.moviejc.domain.feature.home.model.TopRatedMovieVO
import com.hmyh.moviejc.domain.feature.home.model.UpcomingMovieVO

interface MovieRepository {

    suspend fun getNowPlayingMovies(apiKey: String): List<NowPlayingMovieVO>

    suspend fun getPopularMovieList(apiKey: String): List<PopularMovieVO>

    suspend fun getTopRatedMovieList(apiKey: String): List<TopRatedMovieVO>

    suspend fun getUpcomingMovieList(apiKey: String): List<UpcomingMovieVO>

}