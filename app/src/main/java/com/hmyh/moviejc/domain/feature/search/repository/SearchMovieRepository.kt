package com.hmyh.moviejc.domain.feature.search.repository

import com.hmyh.moviejc.domain.feature.search.model.SearchMovieVO

interface SearchMovieRepository {

    suspend fun searchMovies(apiKey: String, query: String): SearchMovieVO
}
