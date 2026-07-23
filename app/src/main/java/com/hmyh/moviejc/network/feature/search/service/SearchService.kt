package com.hmyh.moviejc.network.feature.search.service

import com.hmyh.moviejc.network.extension.GET_SEARCH_MOVIE
import com.hmyh.moviejc.network.extension.PARAM_API_KEY
import com.hmyh.moviejc.network.extension.PARAM_QUERY
import com.hmyh.moviejc.network.feature.search.response.SearchMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET(GET_SEARCH_MOVIE)
    suspend fun searchMovies(
        @Query(PARAM_API_KEY) apiKey: String,
        @Query(PARAM_QUERY) query: String
    ): Response<SearchMovieResponse>
}
