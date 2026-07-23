package com.hmyh.moviejc.data.feature.toprated.mapper

import com.hmyh.moviejc.data.feature.toprated.model.TopRatedMovieItemEntity
import com.hmyh.moviejc.data.feature.toprated.model.TopRatedMoviePageEntity
import com.hmyh.moviejc.domain.feature.toprated.model.TopRatedMovieItemVO
import com.hmyh.moviejc.domain.feature.toprated.model.TopRatedMoviesVO
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import javax.inject.Inject

class TopRatedMovieListEntityMapper @Inject constructor() :
    UnidirectionalMap<TopRatedMoviePageEntity, TopRatedMoviesVO> {

    override fun map(item: TopRatedMoviePageEntity): TopRatedMoviesVO {
        return TopRatedMoviesVO(
            page = item.page,
            movieList = item.movieList?.map(::mapItem),
            totalPages = item.totalPages
        )
    }

    private fun mapItem(item: TopRatedMovieItemEntity): TopRatedMovieItemVO {
        return TopRatedMovieItemVO(
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
