package com.hmyh.moviejc.data.feature.nowplaying.model

data class NowPlayingMoviePageEntity(
    val page: Long? = null,
    val movieList: List<NowPlayingMovieItemEntity>? = null,
    val totalPages: Long? = null
)

data class NowPlayingMovieItemEntity(
    val id: Long,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val voteCount: Long
)
