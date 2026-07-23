package com.hmyh.moviejc.domain.feature.upcoming.usecase

import com.hmyh.moviejc.domain.CoroutineUseCase
import com.hmyh.moviejc.domain.DispatcherProvider
import com.hmyh.moviejc.domain.TwoParams
import com.hmyh.moviejc.domain.feature.upcoming.model.UpcomingMoviesVO
import com.hmyh.moviejc.domain.feature.upcoming.repository.UpcomingMovieRepository
import javax.inject.Inject

/**
 * @param params.one apiKey
 * @param params.two page
 */
class GetUpcomingMovieListUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val upcomingMovieRepository: UpcomingMovieRepository
) : CoroutineUseCase<TwoParams<String, Int>, UpcomingMoviesVO>(dispatcherProvider) {

    override suspend fun provide(params: TwoParams<String, Int>): UpcomingMoviesVO {
        return upcomingMovieRepository.getUpcomingMovies(
            apiKey = params.one,
            page = params.two
        )
    }
}
