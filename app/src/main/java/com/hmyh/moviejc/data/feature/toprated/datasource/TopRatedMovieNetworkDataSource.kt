package com.hmyh.moviejc.data.feature.toprated.datasource

import com.hmyh.moviejc.data.feature.toprated.model.TopRatedMoviePageEntity

interface TopRatedMovieNetworkDataSource {

    suspend fun getTopRatedMovies(apiKey: String, page: Int): TopRatedMoviePageEntity
}
