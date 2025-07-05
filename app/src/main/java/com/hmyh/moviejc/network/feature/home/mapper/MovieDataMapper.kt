package com.hmyh.moviejc.network.feature.home.mapper

import com.hmyh.moviejc.data.feature.home.model.MovieEntity
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import com.hmyh.moviejc.network.feature.home.response.MovieResponse
import javax.inject.Inject

class MovieDataMapper @Inject constructor() :
    UnidirectionalMap<MovieResponse, MovieEntity> {

    override fun map(item: MovieResponse): MovieEntity {
        return MovieEntity(
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