package com.hmyh.moviejc.network.feature.toprated.service

import com.hmyh.moviejc.network.extension.GET_TOP_RATED_MOVIE
import com.hmyh.moviejc.network.extension.PARAM_API_KEY
import com.hmyh.moviejc.network.extension.PARAM_PAGE
import com.hmyh.moviejc.network.feature.toprated.response.TopRatedMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TopRatedService {

    @GET(GET_TOP_RATED_MOVIE)
    suspend fun getTopRatedMovies(
        @Query(PARAM_API_KEY) apiKey: String,
        @Query(PARAM_PAGE) page: Int
    ): Response<TopRatedMovieResponse>
}
