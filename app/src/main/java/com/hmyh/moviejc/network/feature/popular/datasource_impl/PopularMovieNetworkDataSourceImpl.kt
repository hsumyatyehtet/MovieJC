package com.hmyh.moviejc.network.feature.popular.datasource_impl

import com.hmyh.moviejc.data.feature.popular.datasource.PopularMovieNetworkDataSource
import com.hmyh.moviejc.data.feature.popular.model.PopularMoviePageEntity
import com.hmyh.moviejc.network.feature.popular.mapper.PopularMovieListNetworkMapper
import com.hmyh.moviejc.network.feature.popular.service.PopularService
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class PopularMovieNetworkDataSourceImpl @Inject constructor(
    private val service: PopularService,
    private val popularMovieListNetworkMapper: PopularMovieListNetworkMapper
) : PopularMovieNetworkDataSource {

    override suspend fun getPopularMovies(apiKey: String, page: Int): PopularMoviePageEntity {
        return try {
            val response = service.getPopularMovies(apiKey = apiKey, page = page)
            if (response.isSuccessful) {
                response.body()?.let(popularMovieListNetworkMapper::map)
                    ?: PopularMoviePageEntity()
            } else {
                Timber.e("Popular API error: ${response.code()} - ${response.message()}")
                PopularMoviePageEntity()
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: HttpException) {
            Timber.e(e, "Popular network error")
            PopularMoviePageEntity()
        } catch (e: Exception) {
            Timber.e(e, "Popular unexpected error")
            throw e
        }
    }
}
