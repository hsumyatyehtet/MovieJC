package com.hmyh.moviejc.network.feature.popular.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Network response for TMDB /3/movie/popular
 */
@JsonClass(generateAdapter = true)
data class PopularMovieResponse(
    @Json(name = "page") val page: Long? = null,
    @Json(name = "results") val movieList: List<PopularMovieItemResponse>? = null,
    @Json(name = "total_pages") val totalPages: Long? = null
)

@JsonClass(generateAdapter = true)
data class PopularMovieItemResponse(
    @Json(name = "id") val id: Long = 0,
    @Json(name = "original_title") val originalTitle: String? = null,
    @Json(name = "overview") val overview: String? = null,
    @Json(name = "popularity") val popularity: Float? = null,
    @Json(name = "poster_path") val posterPath: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "release_date") val releaseDate: String? = null,
    @Json(name = "vote_count") val voteCount: Long? = null
)
