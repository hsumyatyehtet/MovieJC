package com.hmyh.moviejc.network.feature.upcoming.service

import com.hmyh.moviejc.network.extension.GET_UP_COMING_MOVIE
import com.hmyh.moviejc.network.extension.PARAM_API_KEY
import com.hmyh.moviejc.network.extension.PARAM_PAGE
import com.hmyh.moviejc.network.feature.upcoming.response.UpcomingMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UpcomingService {

    @GET(GET_UP_COMING_MOVIE)
    suspend fun getUpcomingMovies(
        @Query(PARAM_API_KEY) apiKey: String,
        @Query(PARAM_PAGE) page: Int
    ): Response<UpcomingMovieResponse>
}
