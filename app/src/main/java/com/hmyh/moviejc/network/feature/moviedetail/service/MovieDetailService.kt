package com.hmyh.moviejc.network.feature.moviedetail.service

import com.hmyh.moviejc.network.feature.moviedetail.response.MovieDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {

    @GET("/3/movie/{movieId}")
    suspend fun loadNowMovieDetail(
        @Path("movieId")movieId: Long,
        @Query("api_key")apiKey: String
    ): Response<MovieDetailResponse>

}