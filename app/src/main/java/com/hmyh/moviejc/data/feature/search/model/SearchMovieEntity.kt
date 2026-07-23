package com.hmyh.moviejc.data.feature.search.model

data class SearchMovieEntity(
    val page: Long? = null,
    val movieList: List<MovieListEntity>? = null,
    val totalPages: Long? = null
)

data class MovieListEntity(
    val id: Long,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val voteCount: Long
)
