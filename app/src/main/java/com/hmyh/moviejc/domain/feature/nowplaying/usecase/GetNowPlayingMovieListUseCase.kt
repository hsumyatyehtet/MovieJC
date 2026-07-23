package com.hmyh.moviejc.domain.feature.nowplaying.usecase

import com.hmyh.moviejc.domain.CoroutineUseCase
import com.hmyh.moviejc.domain.DispatcherProvider
import com.hmyh.moviejc.domain.TwoParams
import com.hmyh.moviejc.domain.feature.nowplaying.model.NowPlayingMoviesVO
import com.hmyh.moviejc.domain.feature.nowplaying.repository.NowPlayingMovieRepository
import javax.inject.Inject

/**
 * @param params.one apiKey
 * @param params.two page
 */
class GetNowPlayingMovieListUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val nowPlayingMovieRepository: NowPlayingMovieRepository
) : CoroutineUseCase<TwoParams<String, Int>, NowPlayingMoviesVO>(dispatcherProvider) {

    override suspend fun provide(params: TwoParams<String, Int>): NowPlayingMoviesVO {
        return nowPlayingMovieRepository.getNowPlayingMovies(
            apiKey = params.one,
            page = params.two
        )
    }
}
