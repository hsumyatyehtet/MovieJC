package com.hmyh.moviejc.data.feature.search.repository_impl

import com.hmyh.moviejc.data.feature.search.datasource.SearchMovieNetworkDataSource
import com.hmyh.moviejc.data.feature.search.mapper.SearchMovieEntityMapper
import com.hmyh.moviejc.domain.feature.search.model.SearchMovieVO
import com.hmyh.moviejc.domain.feature.search.repository.SearchMovieRepository
import javax.inject.Inject

class SearchMovieRepositoryImpl @Inject constructor(
    private val searchMovieNetworkDataSource: SearchMovieNetworkDataSource,
    private val searchMovieEntityMapper: SearchMovieEntityMapper
) : SearchMovieRepository {

    override suspend fun searchMovies(apiKey: String, query: String, page: Int): SearchMovieVO {
        return searchMovieEntityMapper.map(
            searchMovieNetworkDataSource.searchMovies(
                apiKey = apiKey,
                query = query,
                page = page
            )
        )
    }
}
