package com.hmyh.moviejc.network.feature.moviedetail.service

import com.hmyh.moviejc.network.feature.moviedetail.response.CreditResponse
import com.hmyh.moviejc.network.feature.moviedetail.response.MovieDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {

    @GET(value = "/3/movie/{movieId}")
    suspend fun loadNowMovieDetail(
        @Path("movieId")movieId: Long,
        @Query("api_key")apiKey: String
    ): Response<MovieDetailResponse>

    @GET(value = "/3/movie/{movieId}/credits")
    suspend fun loadCredit(
        @Path(value = "movieId")movieId: Long,
        @Query(value = "api_key")apiKey: String
    ): Response<CreditResponse>

}