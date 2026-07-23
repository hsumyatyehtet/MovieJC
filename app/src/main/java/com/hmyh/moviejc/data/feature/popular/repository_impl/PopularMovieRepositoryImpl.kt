package com.hmyh.moviejc.data.feature.popular.repository_impl

import com.hmyh.moviejc.data.feature.popular.datasource.PopularMovieNetworkDataSource
import com.hmyh.moviejc.data.feature.popular.mapper.PopularMovieListEntityMapper
import com.hmyh.moviejc.domain.feature.popular.model.PopularMoviesVO
import com.hmyh.moviejc.domain.feature.popular.repository.PopularMovieRepository
import javax.inject.Inject

class PopularMovieRepositoryImpl @Inject constructor(
    private val popularMovieNetworkDataSource: PopularMovieNetworkDataSource,
    private val popularMovieListEntityMapper: PopularMovieListEntityMapper
) : PopularMovieRepository {

    override suspend fun getPopularMovies(apiKey: String, page: Int): PopularMoviesVO {
        return popularMovieListEntityMapper.map(
            popularMovieNetworkDataSource.getPopularMovies(
                apiKey = apiKey,
                page = page
            )
        )
    }
}
