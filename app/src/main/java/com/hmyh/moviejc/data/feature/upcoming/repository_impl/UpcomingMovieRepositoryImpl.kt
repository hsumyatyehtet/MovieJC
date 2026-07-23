package com.hmyh.moviejc.data.feature.upcoming.repository_impl

import com.hmyh.moviejc.data.feature.upcoming.datasource.UpcomingMovieNetworkDataSource
import com.hmyh.moviejc.data.feature.upcoming.mapper.UpcomingMovieListEntityMapper
import com.hmyh.moviejc.domain.feature.upcoming.model.UpcomingMoviesVO
import com.hmyh.moviejc.domain.feature.upcoming.repository.UpcomingMovieRepository
import javax.inject.Inject

class UpcomingMovieRepositoryImpl @Inject constructor(
    private val upcomingMovieNetworkDataSource: UpcomingMovieNetworkDataSource,
    private val upcomingMovieListEntityMapper: UpcomingMovieListEntityMapper
) : UpcomingMovieRepository {

    override suspend fun getUpcomingMovies(apiKey: String, page: Int): UpcomingMoviesVO {
        return upcomingMovieListEntityMapper.map(
            upcomingMovieNetworkDataSource.getUpcomingMovies(
                apiKey = apiKey,
                page = page
            )
        )
    }
}
