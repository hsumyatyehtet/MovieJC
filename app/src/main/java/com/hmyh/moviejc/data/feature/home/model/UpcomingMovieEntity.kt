package com.hmyh.moviejc.data.feature.home.model

/**
 * Created by H.M.Y.H on 2025/07/28
 * get data from network and mapping data send to domain layer
 */
data class UpcomingMovieEntity(
    val id: Long,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val voteCount: Long
) {
}