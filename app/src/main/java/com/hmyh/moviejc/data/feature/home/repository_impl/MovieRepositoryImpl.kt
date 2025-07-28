package com.hmyh.moviejc.data.feature.home.repository_impl

import com.hmyh.moviejc.data.feature.home.datasource.MovieNetworkDataSource
import com.hmyh.moviejc.data.feature.home.mapper.NowPlayingMovieEntityMapper
import com.hmyh.moviejc.data.feature.home.mapper.PopularMovieEntityMapper
import com.hmyh.moviejc.data.feature.home.mapper.TopRatedMovieEntityMapper
import com.hmyh.moviejc.data.feature.home.mapper.UpComingMovieEntityMapper
import com.hmyh.moviejc.data.feature.home.model.UpcomingMovieEntity
import com.hmyh.moviejc.domain.feature.home.model.NowPlayingMovieVO
import com.hmyh.moviejc.domain.feature.home.model.PopularMovieVO
import com.hmyh.moviejc.domain.feature.home.model.TopRatedMovieVO
import com.hmyh.moviejc.domain.feature.home.model.UpcomingMovieVO
import com.hmyh.moviejc.domain.feature.home.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieNetworkDataSource: MovieNetworkDataSource,
    private val movieMapper: NowPlayingMovieEntityMapper,
    private val popularMovieEntityMapper: PopularMovieEntityMapper,
    private val topRatedMovieEntityMapper: TopRatedMovieEntityMapper,
    private val upcomingMovieEntityMapper: UpComingMovieEntityMapper
) : MovieRepository{
    override suspend fun getNowPlayingMovies(apiKey: String): List<NowPlayingMovieVO> {
        return movieNetworkDataSource.getNowPlayingMovie(apiKey).map {
            movieMapper.map(it)
        }
    }

    override suspend fun getPopularMovieList(apiKey: String): List<PopularMovieVO> {
        return movieNetworkDataSource.getPopularMovieList(apiKey).map {
            popularMovieEntityMapper.map(it)
        }
    }

    override suspend fun getTopRatedMovieList(apiKey: String): List<TopRatedMovieVO> {
        return movieNetworkDataSource.getTopRatedMovieList(apiKey).map {
            topRatedMovieEntityMapper.map(it)
        }
    }

    override suspend fun getUpcomingMovieList(apiKey: String): List<UpcomingMovieVO> {
        return movieNetworkDataSource.getUpcomingMovieList(apiKey).map {
            upcomingMovieEntityMapper.map(it)
        }
    }

}