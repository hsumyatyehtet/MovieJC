package com.hmyh.moviejc.data.feature.moviedetail.repository_impl

import com.hmyh.moviejc.data.feature.moviedetail.datasoruce.MovieDetailNetworkDataSource
import com.hmyh.moviejc.data.feature.moviedetail.mapper.MovieDetailMapper
import com.hmyh.moviejc.domain.feature.moviedetail.model.MovieDetail
import com.hmyh.moviejc.domain.feature.moviedetail.repository.MovieDetailRepository
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val movieDetailNetworkDataSource: MovieDetailNetworkDataSource,
    private val movieDetailMapper: MovieDetailMapper
): MovieDetailRepository {
    override suspend fun getMovieDetail(
        movieId: Long,
        apiKey: String
    ): MovieDetail {
        return movieDetailNetworkDataSource.getMovieDetail(movieId,apiKey).let {
            movieDetailMapper.map(it)
        }
    }

}