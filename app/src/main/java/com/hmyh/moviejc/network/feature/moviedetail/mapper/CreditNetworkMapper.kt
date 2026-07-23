package com.hmyh.moviejc.network.feature.moviedetail.mapper

import com.hmyh.moviejc.data.feature.moviedetail.model.CreditEntity
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import com.hmyh.moviejc.network.feature.moviedetail.response.CreditResponse
import javax.inject.Inject

class CreditNetworkMapper @Inject constructor():
UnidirectionalMap<CreditResponse, CreditEntity>{
    override fun map(item: CreditResponse): CreditEntity {
        return CreditEntity(
            id = item.id,
            castList = item.castList.map {
                CreditEntity.CastEntity(
                    id = it.id,
                    adult = it.adult,
                    knownForDepartment = it.knowForDepartment,
                    name = it.name,
                    originalName = it.originalName,
                    popularity = it.popularity,
                    profilePath = it.profilePath,
                    castId = it.castId,
                    character = it.character,
                    creditId = it.creditId
                )
            },
            crewList = item.crewList.map {
                CreditEntity.CrewEntity(
                    id = it.id,
                    adult = it.adult,
                    knownForDepartment = it.knownForDepartment,
                    name = it.name,
                    originalName = it.originalName,
                    popularity = it.popularity,
                    profilePath = it.profilePath,
                    creditId = it.creditId,
                    department = it.department,
                    job = it.job
                )
            }
        )
    }
}