package com.hmyh.moviejc.domain.feature.upcoming.repository

import com.hmyh.moviejc.domain.feature.upcoming.model.UpcomingMoviesVO

interface UpcomingMovieRepository {

    suspend fun getUpcomingMovies(apiKey: String, page: Int): UpcomingMoviesVO
}
