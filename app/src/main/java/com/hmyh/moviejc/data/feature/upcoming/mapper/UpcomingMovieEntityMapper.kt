package com.hmyh.moviejc.data.feature.upcoming.mapper

import com.hmyh.moviejc.data.feature.upcoming.model.UpcomingMovieItemEntity
import com.hmyh.moviejc.data.feature.upcoming.model.UpcomingMoviePageEntity
import com.hmyh.moviejc.domain.feature.upcoming.model.UpcomingMovieItemVO
import com.hmyh.moviejc.domain.feature.upcoming.model.UpcomingMoviesVO
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import javax.inject.Inject

class UpcomingMovieListEntityMapper @Inject constructor() :
    UnidirectionalMap<UpcomingMoviePageEntity, UpcomingMoviesVO> {

    override fun map(item: UpcomingMoviePageEntity): UpcomingMoviesVO {
        return UpcomingMoviesVO(
            page = item.page,
            movieList = item.movieList?.map(::mapItem),
            totalPages = item.totalPages
        )
    }

    private fun mapItem(item: UpcomingMovieItemEntity): UpcomingMovieItemVO {
        return UpcomingMovieItemVO(
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
