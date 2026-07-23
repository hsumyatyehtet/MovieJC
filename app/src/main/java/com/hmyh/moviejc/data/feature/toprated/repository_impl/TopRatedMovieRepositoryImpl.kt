package com.hmyh.moviejc.data.feature.toprated.repository_impl

import com.hmyh.moviejc.data.feature.toprated.datasource.TopRatedMovieNetworkDataSource
import com.hmyh.moviejc.data.feature.toprated.mapper.TopRatedMovieListEntityMapper
import com.hmyh.moviejc.domain.feature.toprated.model.TopRatedMoviesVO
import com.hmyh.moviejc.domain.feature.toprated.repository.TopRatedMovieRepository
import javax.inject.Inject

class TopRatedMovieRepositoryImpl @Inject constructor(
    private val topRatedMovieNetworkDataSource: TopRatedMovieNetworkDataSource,
    private val topRatedMovieListEntityMapper: TopRatedMovieListEntityMapper
) : TopRatedMovieRepository {

    override suspend fun getTopRatedMovies(apiKey: String, page: Int): TopRatedMoviesVO {
        return topRatedMovieListEntityMapper.map(
            topRatedMovieNetworkDataSource.getTopRatedMovies(
                apiKey = apiKey,
                page = page
            )
        )
    }
}
