package com.hmyh.moviejc.data.feature.search.mapper

import com.hmyh.moviejc.data.feature.search.model.MovieListEntity
import com.hmyh.moviejc.data.feature.search.model.SearchMovieEntity
import com.hmyh.moviejc.domain.feature.search.model.MovieListVO
import com.hmyh.moviejc.domain.feature.search.model.SearchMovieVO
import com.hmyh.moviejc.domain.mapper.UnidirectionalMap
import javax.inject.Inject

class SearchMovieEntityMapper @Inject constructor() :
    UnidirectionalMap<SearchMovieEntity, SearchMovieVO> {

    override fun map(item: SearchMovieEntity): SearchMovieVO {
        return SearchMovieVO(
            page = item.page,
            movieList = item.movieList?.map(::mapItem),
            totalPages = item.totalPages
        )
    }

    private fun mapItem(item: MovieListEntity): MovieListVO {
        return MovieListVO(
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
