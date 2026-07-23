package com.hmyh.moviejc.network.feature.search.mapper

import com.hmyh.moviejc.data.feature.search.model.MovieListEntity
import com.hmyh.moviejc.data.feature.search.model.SearchMovieEntity
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import com.hmyh.moviejc.network.feature.search.response.SearchMovieItemResponse
import com.hmyh.moviejc.network.feature.search.response.SearchMovieResponse
import javax.inject.Inject

class SearchMovieNetworkMapper @Inject constructor() :
    UnidirectionalMap<SearchMovieResponse, SearchMovieEntity> {

    override fun map(item: SearchMovieResponse): SearchMovieEntity {
        return SearchMovieEntity(
            page = item.page,
            movieList = item.movieList?.map(::mapItem),
            totalPages = item.totalPages
        )
    }

    private fun mapItem(item: SearchMovieItemResponse): MovieListEntity {
        return MovieListEntity(
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
