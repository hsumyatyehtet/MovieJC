package com.hmyh.moviejc.data.feature.nowplaying.repository_impl

import com.hmyh.moviejc.data.feature.nowplaying.datasource.NowPlayingMovieNetworkDataSource
import com.hmyh.moviejc.data.feature.nowplaying.mapper.NowPlayingMovieEntityMapper
import com.hmyh.moviejc.domain.feature.nowplaying.model.NowPlayingMoviesVO
import com.hmyh.moviejc.domain.feature.nowplaying.repository.NowPlayingMovieRepository
import javax.inject.Inject

class NowPlayingMovieRepositoryImpl @Inject constructor(
    private val nowPlayingMovieNetworkDataSource: NowPlayingMovieNetworkDataSource,
    private val nowPlayingMovieEntityMapper: NowPlayingMovieEntityMapper
) : NowPlayingMovieRepository {

    override suspend fun getNowPlayingMovies(apiKey: String, page: Int): NowPlayingMoviesVO {
        return nowPlayingMovieEntityMapper.map(
            nowPlayingMovieNetworkDataSource.getNowPlayingMovies(
                apiKey = apiKey,
                page = page
            )
        )
    }
}
