package com.hmyh.moviejc.domain.feature.popular.usecase

import com.hmyh.moviejc.domain.CoroutineUseCase
import com.hmyh.moviejc.domain.DispatcherProvider
import com.hmyh.moviejc.domain.TwoParams
import com.hmyh.moviejc.domain.feature.popular.model.PopularMoviesVO
import com.hmyh.moviejc.domain.feature.popular.repository.PopularMovieRepository
import javax.inject.Inject

/**
 * @param params.one apiKey
 * @param params.two page
 */
class GetPopularMovieListUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val popularMovieRepository: PopularMovieRepository
) : CoroutineUseCase<TwoParams<String, Int>, PopularMoviesVO>(dispatcherProvider) {

    override suspend fun provide(params: TwoParams<String, Int>): PopularMoviesVO {
        return popularMovieRepository.getPopularMovies(
            apiKey = params.one,
            page = params.two
        )
    }
}
