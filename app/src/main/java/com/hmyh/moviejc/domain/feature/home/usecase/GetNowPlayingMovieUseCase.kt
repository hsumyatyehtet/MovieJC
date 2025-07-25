package com.hmyh.moviejc.domain.feature.home.usecase

import com.hmyh.moviejc.domain.CoroutineUseCase
import com.hmyh.moviejc.domain.DispatcherProvider
import com.hmyh.moviejc.domain.feature.home.model.NowPlayingMovieVO
import com.hmyh.moviejc.domain.feature.home.repository.MovieRepository
import javax.inject.Inject

class GetNowPlayingMovieUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val movieRepository: MovieRepository
) : CoroutineUseCase<String, List<NowPlayingMovieVO>>(dispatcherProvider) {
    override suspend fun provide(params: String): List<NowPlayingMovieVO> {
        return movieRepository.getNowPlayingMovies(params)
    }
}