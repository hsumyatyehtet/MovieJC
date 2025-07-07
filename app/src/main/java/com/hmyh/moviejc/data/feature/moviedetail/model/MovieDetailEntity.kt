package com.hmyh.moviejc.data.feature.moviedetail.model

data class MovieDetailEntity(
    val id: Long,
    val backDropPack: String,
    val originalTitle: String,
    val homePage: String,
    val overView: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: String,
    val genreList: List<GenreEntity>
) {

    data class GenreEntity(
        val id: Long,
        val name: String
    )
}
