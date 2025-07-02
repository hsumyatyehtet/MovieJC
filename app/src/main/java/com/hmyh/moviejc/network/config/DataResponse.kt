package com.hmyh.moviejc.network.config

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
data class PageResponse<T>(
    @Json(name = "meta") val pageMeta: PageMeta?,
    @Json(name = "data") val data: List<T>?,
)

@JsonClass(generateAdapter = true)
data class PageMeta(
    @Json(name = "size") val size: Int?,
    @Json(name = "page") val currentPage: Int?,
    @Json(name = "total_pages") val totalPage: Int?,
    @Json(name = "total_count") val totalCount: Int?
)
