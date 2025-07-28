package com.hmyh.moviejc.data.feature.home.mapper

import com.hmyh.moviejc.data.feature.home.model.UpcomingMovieEntity
import com.hmyh.moviejc.domain.feature.home.model.UpcomingMovieVO
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import javax.inject.Inject

class UpComingMovieEntityMapper @Inject constructor():
UnidirectionalMap<UpcomingMovieEntity, UpcomingMovieVO>{
    override fun map(item: UpcomingMovieEntity): UpcomingMovieVO {
        return UpcomingMovieVO(
            id = item.id,
            originalTitle = item.originalTitle,
            popularity = item.popularity,
            posterPath = item.posterPath,
            title = item.title,
            releaseDate = item.releaseDate,
            voteCount = item.voteCount
        )
    }
}