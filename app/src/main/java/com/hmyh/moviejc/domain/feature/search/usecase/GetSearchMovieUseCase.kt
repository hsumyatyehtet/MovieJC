package com.hmyh.moviejc.domain.feature.search.usecase

import com.hmyh.moviejc.domain.CoroutineUseCase
import com.hmyh.moviejc.domain.DispatcherProvider
import com.hmyh.moviejc.domain.TwoParams
import com.hmyh.moviejc.domain.feature.search.model.SearchMovieVO
import com.hmyh.moviejc.domain.feature.search.repository.SearchMovieRepository
import javax.inject.Inject

/**
 * @param params.one query
 * @param params.two apiKey
 */
class GetSearchMovieUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val searchMovieRepository: SearchMovieRepository
) : CoroutineUseCase<TwoParams<String, String>, SearchMovieVO>(dispatcherProvider) {

    override suspend fun provide(params: TwoParams<String, String>): SearchMovieVO {
        return searchMovieRepository.searchMovies(
            query = params.one,
            apiKey = params.two
        )
    }
}
