package com.hmyh.moviejc.domain.feature.nowplaying.repository

import com.hmyh.moviejc.domain.feature.nowplaying.model.NowPlayingMoviesVO

interface NowPlayingMovieRepository {

    suspend fun getNowPlayingMovies(apiKey: String, page: Int): NowPlayingMoviesVO
}
