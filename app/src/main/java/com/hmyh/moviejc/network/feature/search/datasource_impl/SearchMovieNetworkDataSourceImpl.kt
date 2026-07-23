package com.hmyh.moviejc.network.feature.search.datasource_impl

import com.hmyh.moviejc.data.feature.search.datasource.SearchMovieNetworkDataSource
import com.hmyh.moviejc.data.feature.search.model.SearchMovieEntity
import com.hmyh.moviejc.network.feature.search.mapper.SearchMovieNetworkMapper
import com.hmyh.moviejc.network.feature.search.service.SearchService
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class SearchMovieNetworkDataSourceImpl @Inject constructor(
    private val service: SearchService,
    private val searchMovieNetworkMapper: SearchMovieNetworkMapper
) : SearchMovieNetworkDataSource {

    override suspend fun searchMovies(apiKey: String, query: String): SearchMovieEntity {
        return try {
            val response = service.searchMovies(apiKey = apiKey, query = query)
            if (response.isSuccessful) {
                response.body()?.let(searchMovieNetworkMapper::map)
                    ?: SearchMovieEntity()
            } else {
                Timber.e("Search API error: ${response.code()} - ${response.message()}")
                SearchMovieEntity()
            }
        } catch (e: HttpException) {
            Timber.e(e, "Search network error")
            SearchMovieEntity()
        } catch (e: Exception) {
            Timber.e(e, "Search unexpected error")
            throw e
        }
    }
}
