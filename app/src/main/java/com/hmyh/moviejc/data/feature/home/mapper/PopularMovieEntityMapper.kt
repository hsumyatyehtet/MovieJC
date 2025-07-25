package com.hmyh.moviejc.data.feature.home.mapper

import com.hmyh.moviejc.data.feature.home.model.PopularMovieEntity
import com.hmyh.moviejc.domain.feature.home.model.PopularMovieVO
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import javax.inject.Inject

class PopularMovieEntityMapper @Inject constructor() :
    UnidirectionalMap<PopularMovieEntity, PopularMovieVO> {
    override fun map(item: PopularMovieEntity): PopularMovieVO {
        return PopularMovieVO(
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