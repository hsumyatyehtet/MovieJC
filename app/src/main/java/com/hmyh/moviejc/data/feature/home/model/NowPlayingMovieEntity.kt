package com.hmyh.moviejc.data.feature.home.model

/**
 * Created by H.M.Y.H on 2025/07/24
 * get data from network and mapping data send to domain layer
 */
data class NowPlayingMovieEntity(
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