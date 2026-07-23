package com.hmyh.moviejc.domain.feature.toprated.repository

import com.hmyh.moviejc.domain.feature.toprated.model.TopRatedMoviesVO

interface TopRatedMovieRepository {

    suspend fun getTopRatedMovies(apiKey: String, page: Int): TopRatedMoviesVO
}
