package com.hmyh.moviejc.domain.feature.toprated.usecase

import com.hmyh.moviejc.domain.CoroutineUseCase
import com.hmyh.moviejc.domain.DispatcherProvider
import com.hmyh.moviejc.domain.TwoParams
import com.hmyh.moviejc.domain.feature.toprated.model.TopRatedMoviesVO
import com.hmyh.moviejc.domain.feature.toprated.repository.TopRatedMovieRepository
import javax.inject.Inject

/**
 * @param params.one apiKey
 * @param params.two page
 */
class GetTopRatedMovieListUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val topRatedMovieRepository: TopRatedMovieRepository
) : CoroutineUseCase<TwoParams<String, Int>, TopRatedMoviesVO>(dispatcherProvider) {

    override suspend fun provide(params: TwoParams<String, Int>): TopRatedMoviesVO {
        return topRatedMovieRepository.getTopRatedMovies(
            apiKey = params.one,
            page = params.two
        )
    }
}
