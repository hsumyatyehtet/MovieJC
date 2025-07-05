package com.hmyh.moviejc.data.feature.home.repository_impl

import com.hmyh.moviejc.data.feature.home.datasource.MovieNetworkDataSource
import com.hmyh.moviejc.data.feature.home.mapper.MovieMapper
import com.hmyh.moviejc.domain.feature.home.model.Movie
import com.hmyh.moviejc.domain.feature.home.repository.MovieRepository
import com.hmyh.moviejc.network.feature.home.datasource_impl.MovieNetworkDataSourceImpl
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieNetworkDataSource: MovieNetworkDataSource,
    private val movieMapper: MovieMapper
) : MovieRepository{
    override suspend fun getNowPlayingMovies(apiKey: String): List<Movie> {
        return movieNetworkDataSource.getNowPlayingMovie(apiKey).map {
            movieMapper.map(it)
        }
    }

}