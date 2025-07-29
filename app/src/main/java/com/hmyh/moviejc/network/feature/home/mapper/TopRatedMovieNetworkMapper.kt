package com.hmyh.moviejc.network.feature.home.mapper

import com.hmyh.moviejc.data.feature.home.model.TopRatedMovieEntity
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import com.hmyh.moviejc.network.feature.home.response.MovieResponse
import javax.inject.Inject

class TopRatedMovieNetworkMapper @Inject constructor():
UnidirectionalMap<MovieResponse, TopRatedMovieEntity>{
    override fun map(item: MovieResponse): TopRatedMovieEntity {
        return TopRatedMovieEntity(
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