package com.hmyh.moviejc.domain.feature.moviedetail.usercase

import com.hmyh.moviejc.domain.CoroutineUseCase
import com.hmyh.moviejc.domain.DispatcherProvider
import com.hmyh.moviejc.domain.TwoParams
import com.hmyh.moviejc.domain.feature.moviedetail.model.MovieDetail
import com.hmyh.moviejc.domain.feature.moviedetail.repository.MovieDetailRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val movieDetailRepository: MovieDetailRepository
): CoroutineUseCase<TwoParams<Long, String>, MovieDetail>(dispatcherProvider) {
    override suspend fun provide(params: TwoParams<Long, String>): MovieDetail {
        return movieDetailRepository.getMovieDetail(params.one,params.two)
    }

}