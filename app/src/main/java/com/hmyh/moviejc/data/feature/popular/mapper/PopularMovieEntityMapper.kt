package com.hmyh.moviejc.data.feature.popular.mapper

import com.hmyh.moviejc.data.feature.popular.model.PopularMovieItemEntity
import com.hmyh.moviejc.data.feature.popular.model.PopularMoviePageEntity
import com.hmyh.moviejc.domain.feature.popular.model.PopularMovieItemVO
import com.hmyh.moviejc.domain.feature.popular.model.PopularMoviesVO
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import javax.inject.Inject

class PopularMovieListEntityMapper @Inject constructor() :
    UnidirectionalMap<PopularMoviePageEntity, PopularMoviesVO> {

    override fun map(item: PopularMoviePageEntity): PopularMoviesVO {
        return PopularMoviesVO(
            page = item.page,
            movieList = item.movieList?.map(::mapItem),
            totalPages = item.totalPages
        )
    }

    private fun mapItem(item: PopularMovieItemEntity): PopularMovieItemVO {
        return PopularMovieItemVO(
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
