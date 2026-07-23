package com.hmyh.moviejc.domain.feature.popular.model

import com.hmyh.moviejc.domain.feature.common.domain.MovieDisplayable

data class PopularMoviesVO(
    val page: Long? = null,
    val movieList: List<PopularMovieItemVO>? = null,
    val totalPages: Long? = null
)

data class PopularMovieItemVO(
    override val id: Long,
    val originalTitle: String,
    val popularity: Float,
    override val posterPath: String,
    val title: String,
    val releaseDate: String,
    val voteCount: Long
) : MovieDisplayable
