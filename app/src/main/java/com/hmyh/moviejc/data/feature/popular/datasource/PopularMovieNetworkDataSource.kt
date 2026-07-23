package com.hmyh.moviejc.data.feature.popular.datasource

import com.hmyh.moviejc.data.feature.popular.model.PopularMoviePageEntity

interface PopularMovieNetworkDataSource {

    suspend fun getPopularMovies(apiKey: String, page: Int): PopularMoviePageEntity
}
