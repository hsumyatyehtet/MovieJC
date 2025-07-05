package com.hmyh.moviejc.network.feature.home.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "popularity") val popularity: Float,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "title") val title: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "vote_count") val voteCount: Long
)
