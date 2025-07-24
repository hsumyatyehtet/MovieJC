package com.hmyh.moviejc.domain.feature.home.usecase

import com.hmyh.moviejc.domain.CoroutineUseCase
import com.hmyh.moviejc.domain.DispatcherProvider
import com.hmyh.moviejc.domain.feature.home.model.MovieVO
import com.hmyh.moviejc.domain.feature.home.repository.MovieRepository
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val movieRepository: MovieRepository
) : CoroutineUseCase<String, List<MovieVO>>(dispatcherProvider) {
    override suspend fun provide(params: String): List<MovieVO> {
        return movieRepository.getNowPlayingMovies(params)
    }
}