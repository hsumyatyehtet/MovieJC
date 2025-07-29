package com.hmyh.moviejc.data.feature.home.mapper

import com.hmyh.moviejc.data.feature.home.model.NowPlayingMovieEntity
import com.hmyh.moviejc.domain.feature.home.model.NowPlayingMovieVO
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import javax.inject.Inject

/**
 * Created by H.M.Y.H on 2025/07/24
 * map data send to domain layer
 */
class NowPlayingMovieEntityMapper @Inject constructor()
    : UnidirectionalMap<NowPlayingMovieEntity,NowPlayingMovieVO> {
    override fun map(item: NowPlayingMovieEntity): NowPlayingMovieVO {
        return NowPlayingMovieVO(
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