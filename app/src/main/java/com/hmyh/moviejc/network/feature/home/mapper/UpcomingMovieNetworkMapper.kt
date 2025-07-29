package com.hmyh.moviejc.network.feature.home.mapper

import com.hmyh.moviejc.data.feature.home.model.UpcomingMovieEntity
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import com.hmyh.moviejc.network.feature.home.response.MovieResponse
import javax.inject.Inject

class UpcomingMovieNetworkMapper @Inject constructor() :
UnidirectionalMap<MovieResponse, UpcomingMovieEntity>{
    override fun map(item: MovieResponse): UpcomingMovieEntity {
        return UpcomingMovieEntity(
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