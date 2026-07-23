package com.hmyh.moviejc.network.feature.upcoming.mapper

import com.hmyh.moviejc.data.feature.upcoming.model.UpcomingMovieItemEntity
import com.hmyh.moviejc.data.feature.upcoming.model.UpcomingMoviePageEntity
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import com.hmyh.moviejc.network.feature.upcoming.response.UpcomingMovieItemResponse
import com.hmyh.moviejc.network.feature.upcoming.response.UpcomingMovieResponse
import javax.inject.Inject

class UpcomingMovieListNetworkMapper @Inject constructor() :
    UnidirectionalMap<UpcomingMovieResponse, UpcomingMoviePageEntity> {

    override fun map(item: UpcomingMovieResponse): UpcomingMoviePageEntity {
        return UpcomingMoviePageEntity(
            page = item.page,
            movieList = item.movieList?.map(::mapItem),
            totalPages = item.totalPages
        )
    }

    private fun mapItem(item: UpcomingMovieItemResponse): UpcomingMovieItemEntity {
        return UpcomingMovieItemEntity(
            id = item.id,
            originalTitle = item.originalTitle.orEmpty(),
            overview = item.overview.orEmpty(),
            popularity = item.popularity ?: 0f,
            posterPath = item.posterPath.orEmpty(),
            title = item.title.orEmpty(),
            releaseDate = item.releaseDate.orEmpty(),
            voteCount = item.voteCount ?: 0
        )
    }
}
