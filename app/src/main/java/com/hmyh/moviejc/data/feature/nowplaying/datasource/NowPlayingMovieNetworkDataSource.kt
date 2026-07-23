package com.hmyh.moviejc.data.feature.nowplaying.datasource

import com.hmyh.moviejc.data.feature.nowplaying.model.NowPlayingMoviePageEntity

interface NowPlayingMovieNetworkDataSource {

    suspend fun getNowPlayingMovies(apiKey: String, page: Int): NowPlayingMoviePageEntity
}
