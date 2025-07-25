package com.hmyh.moviejc.data.feature.home.datasource

import com.hmyh.moviejc.data.feature.home.model.NowPlayingMovieEntity
import com.hmyh.moviejc.data.feature.home.model.PopularMovieEntity

interface MovieNetworkDataSource {

    suspend fun getNowPlayingMovie(apiKey: String): List<NowPlayingMovieEntity>

    suspend fun getPopularMovieList(apiKey: String): List<PopularMovieEntity>

}