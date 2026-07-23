package com.hmyh.moviejc.data.feature.search.datasource

import com.hmyh.moviejc.data.feature.search.model.SearchMovieEntity

interface SearchMovieNetworkDataSource {

    suspend fun searchMovies(apiKey: String, query: String, page: Int): SearchMovieEntity
}
