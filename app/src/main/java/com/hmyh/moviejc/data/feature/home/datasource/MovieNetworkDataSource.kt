package com.hmyh.moviejc.data.feature.home.datasource

import com.hmyh.moviejc.data.feature.home.model.MovieEntity
import com.hmyh.moviejc.domain.feature.home.repository.MovieRepository

interface MovieNetworkDataSource {

    suspend fun getNowPlayingMovie(apiKey: String): List<MovieEntity>

}