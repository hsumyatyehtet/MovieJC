package com.hmyh.moviejc.network.feature.home.mapper

import com.hmyh.moviejc.data.feature.home.model.NowPlayingMovieEntity
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import com.hmyh.moviejc.network.feature.home.response.MovieResponse
import javax.inject.Inject

/**
 * Created by H.M.Y.H on 2025/07/24
 * map data send to data layer
 */
class NowPlayingMovieNetworkMapper @Inject constructor() :
    UnidirectionalMap<MovieResponse, NowPlayingMovieEntity> {

    override fun map(item: MovieResponse): NowPlayingMovieEntity {
        return NowPlayingMovieEntity(
            id = item.id,
            originalTitle = item.originalTitle,
            overview = item.overview,
            popularity = item.popularity,
            posterPath = item.posterPath,
            title = item.title,
            releaseDate = item.releaseDate,
            voteCount = item.voteCount
        )
    }

}