package com.hmyh.moviejc.network.feature.home.mapper

import com.hmyh.moviejc.data.feature.home.model.PopularMovieEntity
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import com.hmyh.moviejc.network.feature.home.response.MovieResponse
import javax.inject.Inject

class PopularMovieNetworkMapper @Inject constructor() :
    UnidirectionalMap<MovieResponse, PopularMovieEntity> {
    override fun map(item: MovieResponse): PopularMovieEntity {
        return PopularMovieEntity(
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