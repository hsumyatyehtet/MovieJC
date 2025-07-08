package com.hmyh.moviejc.domain.feature.moviedetail.model

data class MovieDetail(
    val id: Long,
    val backDropPack: String,
    val originalTitle: String,
    val homePage: String,
    val overView: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: String,
    val genreList: List<GenreEntity?>,
    val runtime: Int
) {

    data class GenreEntity(
        val id: Long,
        val name: String
    )
}
