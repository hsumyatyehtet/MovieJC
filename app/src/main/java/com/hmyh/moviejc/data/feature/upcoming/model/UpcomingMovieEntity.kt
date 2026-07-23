package com.hmyh.moviejc.data.feature.upcoming.model

data class UpcomingMoviePageEntity(
    val page: Long? = null,
    val movieList: List<UpcomingMovieItemEntity>? = null,
    val totalPages: Long? = null
)

data class UpcomingMovieItemEntity(
    val id: Long,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val voteCount: Long
)
