package com.hmyh.moviejc.domain.feature.home.usecase

import com.hmyh.moviejc.domain.CoroutineUseCase
import com.hmyh.moviejc.domain.DispatcherProvider
import com.hmyh.moviejc.domain.feature.home.model.PopularMovieVO
import com.hmyh.moviejc.domain.feature.home.repository.MovieRepository
import javax.inject.Inject

class GetPopularMovieUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val movieRepository: MovieRepository
): CoroutineUseCase<String, List<PopularMovieVO>>(dispatcherProvider){
    override suspend fun provide(params: String): List<PopularMovieVO> {
        return movieRepository.getPopularMovieList(apiKey = params)
    }
}