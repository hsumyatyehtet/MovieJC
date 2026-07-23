package com.hmyh.moviejc.network.feature.popular.mapper

import com.hmyh.moviejc.data.feature.popular.model.PopularMovieItemEntity
import com.hmyh.moviejc.data.feature.popular.model.PopularMoviePageEntity
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import com.hmyh.moviejc.network.feature.popular.response.PopularMovieItemResponse
import com.hmyh.moviejc.network.feature.popular.response.PopularMovieResponse
import javax.inject.Inject

class PopularMovieListNetworkMapper @Inject constructor() :
    UnidirectionalMap<PopularMovieResponse, PopularMoviePageEntity> {

    override fun map(item: PopularMovieResponse): PopularMoviePageEntity {
        return PopularMoviePageEntity(
            page = item.page,
            movieList = item.movieList?.map(::mapItem),
            totalPages = item.totalPages
        )
    }

    private fun mapItem(item: PopularMovieItemResponse): PopularMovieItemEntity {
        return PopularMovieItemEntity(
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
