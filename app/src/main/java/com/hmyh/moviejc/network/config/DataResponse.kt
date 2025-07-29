package com.hmyh.moviejc.network.config

import com.hmyh.moviejc.network.feature.home.response.MovieResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by H.M.Y.H on 2/07/2025.
 */

@JsonClass(generateAdapter = true)
data class DataResponse<T>(
    @Json(name = "data") val data: T?,
    @Json(name = "message") val errorMessage: String?,
    @Json(name = "success") val success: Any?
)

@JsonClass(generateAdapter = true)
data class DataEmptyResponse(
    @Json(name = "message") val message: String?
)

@JsonClass(generateAdapter = true)
data class PageResponse(
    @Json(name = "dates")val dates: Date?=null,
    @Json(name = "page") val currentPage: Int,
    @Json(name = "results") val results: List<MovieResponse>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Long,
)

@JsonClass(generateAdapter = true)
data class Date(
    @Json(name = "maximum")val maximum: String?,
    @Json(name = "minimum")val minimum: String
)


@JsonClass(generateAdapter = true)
data class PageMeta(
    @Json(name = "page") val currentPage: Int?,
    @Json(name = "total_pages") val totalPage: Int?,
    @Json(name = "total_results") val totalResults: Int?
)
