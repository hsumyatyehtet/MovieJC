package com.hmyh.moviejc.network.feature.nowplaying.mapper

import com.hmyh.moviejc.data.feature.nowplaying.model.NowPlayingMovieItemEntity
import com.hmyh.moviejc.data.feature.nowplaying.model.NowPlayingMoviePageEntity
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import com.hmyh.moviejc.network.feature.nowplaying.response.NowPlayingMovieItemResponse
import com.hmyh.moviejc.network.feature.nowplaying.response.NowPlayingMovieResponse
import javax.inject.Inject

class NowPlayingMovieNetworkMapper @Inject constructor() :
    UnidirectionalMap<NowPlayingMovieResponse, NowPlayingMoviePageEntity> {

    override fun map(item: NowPlayingMovieResponse): NowPlayingMoviePageEntity {
        return NowPlayingMoviePageEntity(
            page = item.page,
            movieList = item.movieList?.map(::mapItem),
            totalPages = item.totalPages
        )
    }

    private fun mapItem(item: NowPlayingMovieItemResponse): NowPlayingMovieItemEntity {
        return NowPlayingMovieItemEntity(
            id = item.id,
            originalTitle = item.originalTitle.orEmpty(),
            overview = item.overview.orEmpty(),
            popularity = item.popularity ?: 0f,
            posterPath = item.posterPath.orEmpty(),
            title = item.title.orEmpty(),
            releaseDate = item.releaseDate.orEmpty(),
            voteCount = item.voteCount ?: 0
        )
    }
}
