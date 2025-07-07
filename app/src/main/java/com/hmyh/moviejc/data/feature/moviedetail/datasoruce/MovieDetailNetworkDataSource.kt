package com.hmyh.moviejc.data.feature.moviedetail.datasoruce

import com.hmyh.moviejc.data.feature.moviedetail.model.MovieDetailEntity

interface MovieDetailNetworkDataSource {

    suspend fun getMovieDetail(movieId: Long,apiKey: String): MovieDetailEntity

}