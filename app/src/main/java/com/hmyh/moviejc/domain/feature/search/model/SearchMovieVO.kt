package com.hmyh.moviejc.domain.feature.search.model

import com.hmyh.moviejc.domain.feature.common.domain.MovieDisplayable

data class SearchMovieVO(
    val page: Long? = null,
    val movieList: List<MovieListVO>? = null,
    val totalPages: Long? = null
)

data class MovieListVO(
    override val id: Long,
    val originalTitle: String,
    val popularity: Float,
    override val posterPath: String,
    val title: String,
    val releaseDate: String,
    val voteCount: Long
) : MovieDisplayable
