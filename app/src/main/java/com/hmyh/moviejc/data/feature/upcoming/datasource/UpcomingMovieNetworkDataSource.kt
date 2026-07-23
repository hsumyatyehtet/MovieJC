package com.hmyh.moviejc.data.feature.upcoming.datasource

import com.hmyh.moviejc.data.feature.upcoming.model.UpcomingMoviePageEntity

interface UpcomingMovieNetworkDataSource {

    suspend fun getUpcomingMovies(apiKey: String, page: Int): UpcomingMoviePageEntity
}
