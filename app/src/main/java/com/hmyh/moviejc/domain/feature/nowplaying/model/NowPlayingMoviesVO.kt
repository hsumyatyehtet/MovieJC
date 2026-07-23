package com.hmyh.moviejc.domain.feature.nowplaying.model

import com.hmyh.moviejc.domain.feature.common.domain.MovieDisplayable

data class NowPlayingMoviesVO(
    val page: Long? = null,
    val movieList: List<NowPlayingMovieItemVO>? = null,
    val totalPages: Long? = null
)

data class NowPlayingMovieItemVO(
    override val id: Long,
    val originalTitle: String,
    val popularity: Float,
    override val posterPath: String,
    val title: String,
    val releaseDate: String,
    val voteCount: Long
) : MovieDisplayable
