package com.hmyh.moviejc.network.feature.moviedetail.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailResponse(
    @Json(name = "id")val id: Long,
    @Json(name = "backdrop_path")val backDropPack: String,
    @Json(name = "original_title")val originalTitle: String,
    @Json(name = "homepage")val homePage: String,
    @Json(name = "origin_country")val originalCountry: List<String>,
    @Json(name = "overview")val overView: String,
    @Json(name = "poster_path")val posterPath: String,
    @Json(name = "release_date")val releaseDate: String,
    @Json(name = "title")val title: String,
    @Json(name = "vote_average")val voteAverage: String,
    @Json(name = "genres")val genreList: List<GenreVO>,
    @Json(name = "runtime")val runtime: Int
) {
    @JsonClass(generateAdapter = true)
    data class GenreVO(
        @Json(name = "id")val id: Long,
        @Json(name = "name")val name: String
    )
}
