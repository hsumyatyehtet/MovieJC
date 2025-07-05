package com.hmyh.moviejc.network.feature.home.service


import com.hmyh.moviejc.network.config.PageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {

    @GET("/3/movie/now_playing")
    suspend fun loadNowPlayingMovies(
        @Query("api_key")apiKey: String
    ): Response<PageResponse>

}