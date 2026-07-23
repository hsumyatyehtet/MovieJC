package com.hmyh.moviejc.network.feature.nowplaying.service

import com.hmyh.moviejc.network.extension.GET_NOW_PLAYING_MOVIE_LIST
import com.hmyh.moviejc.network.extension.PARAM_API_KEY
import com.hmyh.moviejc.network.extension.PARAM_PAGE
import com.hmyh.moviejc.network.feature.nowplaying.response.NowPlayingMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NowPlayingService {

    @GET(GET_NOW_PLAYING_MOVIE_LIST)
    suspend fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey: String,
        @Query(PARAM_PAGE) page: Int
    ): Response<NowPlayingMovieResponse>
}
