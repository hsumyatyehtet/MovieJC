package com.hmyh.moviejc.data.feature.nowplaying.mapper

import com.hmyh.moviejc.data.feature.nowplaying.model.NowPlayingMovieItemEntity
import com.hmyh.moviejc.data.feature.nowplaying.model.NowPlayingMoviePageEntity
import com.hmyh.moviejc.domain.feature.nowplaying.model.NowPlayingMovieItemVO
import com.hmyh.moviejc.domain.feature.nowplaying.model.NowPlayingMoviesVO
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import javax.inject.Inject

class NowPlayingMovieEntityMapper @Inject constructor() :
    UnidirectionalMap<NowPlayingMoviePageEntity, NowPlayingMoviesVO> {

    override fun map(item: NowPlayingMoviePageEntity): NowPlayingMoviesVO {
        return NowPlayingMoviesVO(
            page = item.page,
            movieList = item.movieList?.map(::mapItem),
            totalPages = item.totalPages
        )
    }

    private fun mapItem(item: NowPlayingMovieItemEntity): NowPlayingMovieItemVO {
        return NowPlayingMovieItemVO(
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
