package com.hmyh.moviejc.domain.feature.upcoming.model

import com.hmyh.moviejc.domain.feature.common.domain.MovieDisplayable

data class UpcomingMoviesVO(
    val page: Long? = null,
    val movieList: List<UpcomingMovieItemVO>? = null,
    val totalPages: Long? = null
)

data class UpcomingMovieItemVO(
    override val id: Long,
    val originalTitle: String,
    val popularity: Float,
    override val posterPath: String,
    val title: String,
    val releaseDate: String,
    val voteCount: Long
) : MovieDisplayable
