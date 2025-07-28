package com.hmyh.moviejc.data.feature.home.datasource

import com.hmyh.moviejc.data.feature.home.model.NowPlayingMovieEntity
import com.hmyh.moviejc.data.feature.home.model.PopularMovieEntity
import com.hmyh.moviejc.data.feature.home.model.TopRatedMovieEntity

interface MovieNetworkDataSource {

    suspend fun getNowPlayingMovie(apiKey: String): List<NowPlayingMovieEntity>

    suspend fun getPopularMovieList(apiKey: String): List<PopularMovieEntity>

    suspend fun getTopRatedMovieList(apiKey: String): List<TopRatedMovieEntity>

}