package com.hmyh.moviejc.domain.feature.search.usecase

import com.hmyh.moviejc.domain.CoroutineUseCase
import com.hmyh.moviejc.domain.DispatcherProvider
import com.hmyh.moviejc.domain.ThreeParams
import com.hmyh.moviejc.domain.feature.search.model.SearchMovieVO
import com.hmyh.moviejc.domain.feature.search.repository.SearchMovieRepository
import javax.inject.Inject

/**
 * @param params.one query
 * @param params.two apiKey
 * @param params.three page
 */
class GetSearchMovieUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val searchMovieRepository: SearchMovieRepository
) : CoroutineUseCase<ThreeParams<String, String, Int>, SearchMovieVO>(dispatcherProvider) {

    override suspend fun provide(params: ThreeParams<String, String, Int>): SearchMovieVO {
        return searchMovieRepository.searchMovies(
            query = params.one,
            apiKey = params.two,
            page = params.three
        )
    }
}
