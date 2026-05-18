package com.hmyh.moviejc.network.feature.moviedetail.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreditResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "cast") val castList: List<CastResponse>,
    @Json(name = "crew") val crewList: List<CrewResponse>
) {

    @JsonClass(generateAdapter = true)
    data class CastResponse(
        @Json(name = "id") val id: Long,
        @Json(name = "adult") val adult: Boolean,
        @Json(name = "known_for_department") val knowForDepartment: String,
        @Json(name = "name") val name: String,
        @Json(name = "original_name") val originalName: String,
        @Json(name = "popularity") val popularity: Double,
        @Json(name = "profile_path") val profilePath: String,
        @Json(name = "cast_id") val castId: Long,
        @Json(name = "character") val character: String,
        @Json(name = "credit_id") val creditId: String
    )

    @JsonClass(generateAdapter = true)
    data class CrewResponse(
        @Json(name = "id")val id: Long,
        @Json(name = "adult") val adult: Boolean,
        @Json(name = "known_for_department") val knownForDepartment: String,
        @Json(name = "name") val name: String,
        @Json(name = "original_name") val originalName: String,
        @Json(name = "popularity") val popularity: Double,
        @Json(name = "profile_path") val profilePath: String,
        @Json(name = "credit_id") val creditId: String,
        @Json(name = "department") val department: String,
        @Json(name = "job") val job: String
    )

}