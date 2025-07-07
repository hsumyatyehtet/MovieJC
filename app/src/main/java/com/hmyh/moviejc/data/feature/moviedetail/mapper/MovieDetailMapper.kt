package com.hmyh.moviejc.data.feature.moviedetail.mapper

import com.hmyh.moviejc.data.feature.home.model.MovieEntity
import com.hmyh.moviejc.data.feature.moviedetail.model.MovieDetailEntity
import com.hmyh.moviejc.domain.feature.moviedetail.model.MovieDetail
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import javax.inject.Inject

class MovieDetailMapper @Inject constructor()
    : UnidirectionalMap<MovieDetailEntity, MovieDetail> {
    override fun map(item: MovieDetailEntity): MovieDetail {
        return MovieDetail(
            id = item.id,
            backDropPack = item.backDropPack,
            originalTitle = item.originalTitle,
            homePage = item.homePage,
            overView = item.overView,
            posterPath = item.posterPath,
            releaseDate = item.releaseDate,
            title = item.title,
            voteAverage = item.voteAverage,
            genreList = item.genreList.map {
                MovieDetail.GenreEntity(
                    id = it.id,
                    name = it.name
                )
            }
        )
    }
}