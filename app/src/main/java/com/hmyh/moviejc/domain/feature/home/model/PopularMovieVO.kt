package com.hmyh.moviejc.domain.feature.home.model

import com.hmyh.moviejc.domain.feature.common.domain.MovieDisplayable

/**
 * Created by H.M.Y.H on 2025/07/24
 * get data from dat layer and then to sent ready data to UI layer
 */
data class PopularMovieVO(
    override val id: Long,
    val originalTitle: String,
    val popularity: Float,
    override val posterPath: String,
    val title: String,
    val releaseDate: String,
    val voteCount: Long
): MovieDisplayable {
}