package com.hmyh.moviejc.network.feature.moviedetail.datasource_impl

import com.hmyh.moviejc.data.feature.moviedetail.datasoruce.MovieDetailNetworkDataSource
import com.hmyh.moviejc.data.feature.moviedetail.model.MovieDetailEntity
import com.hmyh.moviejc.network.extension.getBody
import com.hmyh.moviejc.network.feature.moviedetail.mapper.MovieDetailMapper
import com.hmyh.moviejc.network.feature.moviedetail.service.MovieDetailService
import javax.inject.Inject

class MovieDetailNetworkDataSourceImpl @Inject constructor(
    private val movieDetailService: MovieDetailService,
    private val movieDetailMapper: MovieDetailMapper
): MovieDetailNetworkDataSource {
    override suspend fun getMovieDetail(
        movieId: Long,
        apiKey: String
    ): MovieDetailEntity {
        val response = movieDetailService.loadNowMovieDetail(movieId = movieId,apiKey = apiKey)
        return if (response.isSuccessful) {
            val body = response.getBody()
            movieDetailMapper.map(body)
        } else {
            throw Exception("Error fetching movie details: ${response.code()} - ${response.message()}")
        }
    }
}