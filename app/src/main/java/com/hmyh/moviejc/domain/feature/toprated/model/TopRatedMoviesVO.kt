package com.hmyh.moviejc.domain.feature.toprated.model

import com.hmyh.moviejc.domain.feature.common.domain.MovieDisplayable

data class TopRatedMoviesVO(
    val page: Long? = null,
    val movieList: List<TopRatedMovieItemVO>? = null,
    val totalPages: Long? = null
)

data class TopRatedMovieItemVO(
    override val id: Long,
    val originalTitle: String,
    val popularity: Float,
    override val posterPath: String,
    val title: String,
    val releaseDate: String,
    val voteCount: Long
) : MovieDisplayable
