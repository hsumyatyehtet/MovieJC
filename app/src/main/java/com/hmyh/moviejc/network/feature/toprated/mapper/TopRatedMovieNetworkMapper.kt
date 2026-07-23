package com.hmyh.moviejc.network.feature.toprated.mapper

import com.hmyh.moviejc.data.feature.toprated.model.TopRatedMovieItemEntity
import com.hmyh.moviejc.data.feature.toprated.model.TopRatedMoviePageEntity
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import com.hmyh.moviejc.network.feature.toprated.response.TopRatedMovieItemResponse
import com.hmyh.moviejc.network.feature.toprated.response.TopRatedMovieResponse
import javax.inject.Inject

class TopRatedMovieListNetworkMapper @Inject constructor() :
    UnidirectionalMap<TopRatedMovieResponse, TopRatedMoviePageEntity> {

    override fun map(item: TopRatedMovieResponse): TopRatedMoviePageEntity {
        return TopRatedMoviePageEntity(
            page = item.page,
            movieList = item.movieList?.map(::mapItem),
            totalPages = item.totalPages
        )
    }

    private fun mapItem(item: TopRatedMovieItemResponse): TopRatedMovieItemEntity {
        return TopRatedMovieItemEntity(
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
