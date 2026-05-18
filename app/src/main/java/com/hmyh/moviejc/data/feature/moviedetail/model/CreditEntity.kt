package com.hmyh.moviejc.data.feature.moviedetail.model

data class CreditEntity(
    val id: Long,
    val castList: List<CastEntity>,
    val crewList: List<CrewEntity>
) {

    data class CastEntity(
        val id: Long,
        val adult: Boolean,
        val knownForDepartment: String,
        val name: String,
        val originalName: String,
        val popularity: Double,
        val profilePath: String,
        val castId: Long,
        val character: String,
        val creditId: String
    )

    data class CrewEntity(
        val id: Long,
        val adult: Boolean,
        val knownForDepartment: String,
        val name: String,
        val originalName: String,
        val popularity: Double,
        val profilePath: String,
        val creditId: String,
        val department: String,
        val job: String
    )

}