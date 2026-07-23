package com.hmyh.moviejc.network.feature.popular.service

import com.hmyh.moviejc.network.extension.GET_POPULAR_MOVIE_LIST
import com.hmyh.moviejc.network.extension.PARAM_API_KEY
import com.hmyh.moviejc.network.extension.PARAM_PAGE
import com.hmyh.moviejc.network.feature.popular.response.PopularMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularService {

    @GET(GET_POPULAR_MOVIE_LIST)
    suspend fun getPopularMovies(
        @Query(PARAM_API_KEY) apiKey: String,
        @Query(PARAM_PAGE) page: Int
    ): Response<PopularMovieResponse>
}
