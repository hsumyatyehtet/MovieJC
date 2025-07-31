package com.hmyh.moviejc.network.feature.moviedetail.mapper

import com.hmyh.moviejc.data.feature.moviedetail.model.MovieDetailEntity
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import com.hmyh.moviejc.network.feature.moviedetail.response.MovieDetailResponse
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() :
    UnidirectionalMap<MovieDetailResponse, MovieDetailEntity> {
    override fun map(item: MovieDetailResponse): MovieDetailEntity {

        return MovieDetailEntity(
            id = item.id,
            backDropPack = item.backDropPack,
            originalTitle = item.originalTitle,
            homePage = item.homePage,
            originalCountry = item.originalCountry,
            overView = item.overView,
            posterPath = item.posterPath,
            releaseDate = item.releaseDate,
            title = item.title,
            voteAverage = item.voteAverage,
            genreList = item.genreList.map {
                MovieDetailEntity.GenreEntity(
                    id = it.id,
                    name = it.name
                )
            },
            runtime = item.runtime
        )
    }
}