package com.hmyh.moviejc.domain.feature.moviedetail.repository

import com.hmyh.moviejc.domain.feature.moviedetail.model.MovieDetail

interface MovieDetailRepository {

    suspend fun getMovieDetail(movieId: Long,apiKey: String): MovieDetail

}