package com.hmyh.moviejc.domain.feature.home.usecase

import com.hmyh.moviejc.domain.CoroutineUseCase
import com.hmyh.moviejc.domain.DispatcherProvider
import com.hmyh.moviejc.domain.feature.home.model.TopRatedMovieVO
import com.hmyh.moviejc.domain.feature.home.repository.MovieRepository
import javax.inject.Inject

class GetTopRatedMovieUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val movieRepository: MovieRepository
): CoroutineUseCase<String, List<TopRatedMovieVO>>(dispatcherProvider) {
    override suspend fun provide(params: String): List<TopRatedMovieVO> {
        return movieRepository.getTopRatedMovieList(params)
    }
}