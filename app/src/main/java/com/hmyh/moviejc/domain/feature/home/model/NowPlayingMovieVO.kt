package com.hmyh.moviejc.domain.feature.home.model

/**
 * Created by H.M.Y.H on 2025/07/05
 * get data from dat layer and then to sent ready data to UI layer
 */
data class NowPlayingMovieVO(
    val id: Long,
    val originalTitle: String,
    val popularity: Float,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val voteCount: Long
) {
}