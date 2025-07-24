package com.hmyh.moviejc.data.feature.home.mapper

import com.hmyh.moviejc.data.feature.home.model.MovieEntity
import com.hmyh.moviejc.domain.feature.home.model.MovieVO
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import javax.inject.Inject

class MovieMapper @Inject constructor()
    : UnidirectionalMap<MovieEntity,MovieVO> {
    override fun map(item: MovieEntity): MovieVO {
        return MovieVO(
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