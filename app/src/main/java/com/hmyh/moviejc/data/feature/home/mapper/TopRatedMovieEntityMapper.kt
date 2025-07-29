package com.hmyh.moviejc.data.feature.home.mapper

import com.hmyh.moviejc.data.feature.home.model.TopRatedMovieEntity
import com.hmyh.moviejc.domain.feature.home.model.TopRatedMovieVO
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import javax.inject.Inject

class TopRatedMovieEntityMapper @Inject constructor():
UnidirectionalMap<TopRatedMovieEntity, TopRatedMovieVO>{
    override fun map(item: TopRatedMovieEntity): TopRatedMovieVO {
        return TopRatedMovieVO(
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