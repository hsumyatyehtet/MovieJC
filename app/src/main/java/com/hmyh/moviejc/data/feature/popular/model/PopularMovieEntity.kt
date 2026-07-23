package com.hmyh.moviejc.data.feature.popular.model

data class PopularMoviePageEntity(
    val page: Long? = null,
    val movieList: List<PopularMovieItemEntity>? = null,
    val totalPages: Long? = null
)

data class PopularMovieItemEntity(
    val id: Long,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val voteCount: Long
)
