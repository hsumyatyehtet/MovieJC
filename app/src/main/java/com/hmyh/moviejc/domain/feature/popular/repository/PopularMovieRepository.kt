package com.hmyh.moviejc.domain.feature.popular.repository

import com.hmyh.moviejc.domain.feature.popular.model.PopularMoviesVO

interface PopularMovieRepository {

    suspend fun getPopularMovies(apiKey: String, page: Int): PopularMoviesVO
}
