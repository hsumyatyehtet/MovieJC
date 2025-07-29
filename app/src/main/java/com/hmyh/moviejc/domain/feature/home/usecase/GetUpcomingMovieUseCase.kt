package com.hmyh.moviejc.domain.feature.home.usecase

import com.hmyh.moviejc.domain.CoroutineUseCase
import com.hmyh.moviejc.domain.DispatcherProvider
import com.hmyh.moviejc.domain.feature.home.model.UpcomingMovieVO
import com.hmyh.moviejc.domain.feature.home.repository.MovieRepository
import javax.inject.Inject

class GetUpcomingMovieUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val movieRepository: MovieRepository
): CoroutineUseCase<String, List<UpcomingMovieVO>>(dispatcherProvider) {
    override suspend fun provide(params: String): List<UpcomingMovieVO> {
        return movieRepository.getUpcomingMovieList(apiKey = params)
    }

}