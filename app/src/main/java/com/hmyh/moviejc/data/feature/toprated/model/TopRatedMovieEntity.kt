package com.hmyh.moviejc.data.feature.toprated.model

data class TopRatedMoviePageEntity(
    val page: Long? = null,
    val movieList: List<TopRatedMovieItemEntity>? = null,
    val totalPages: Long? = null
)

data class TopRatedMovieItemEntity(
    val id: Long,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val voteCount: Long
)
