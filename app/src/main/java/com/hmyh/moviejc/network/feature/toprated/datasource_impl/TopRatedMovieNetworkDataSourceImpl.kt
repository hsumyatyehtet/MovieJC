package com.hmyh.moviejc.network.feature.toprated.datasource_impl

import com.hmyh.moviejc.data.feature.toprated.datasource.TopRatedMovieNetworkDataSource
import com.hmyh.moviejc.data.feature.toprated.model.TopRatedMoviePageEntity
import com.hmyh.moviejc.network.feature.toprated.mapper.TopRatedMovieListNetworkMapper
import com.hmyh.moviejc.network.feature.toprated.service.TopRatedService
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class TopRatedMovieNetworkDataSourceImpl @Inject constructor(
    private val service: TopRatedService,
    private val topRatedMovieListNetworkMapper: TopRatedMovieListNetworkMapper
) : TopRatedMovieNetworkDataSource {

    override suspend fun getTopRatedMovies(apiKey: String, page: Int): TopRatedMoviePageEntity {
        return try {
            val response = service.getTopRatedMovies(apiKey = apiKey, page = page)
            if (response.isSuccessful) {
                response.body()?.let(topRatedMovieListNetworkMapper::map)
                    ?: TopRatedMoviePageEntity()
            } else {
                Timber.e("Top Rated API error: ${response.code()} - ${response.message()}")
                TopRatedMoviePageEntity()
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: HttpException) {
            Timber.e(e, "Top Rated network error")
            TopRatedMoviePageEntity()
        } catch (e: Exception) {
            Timber.e(e, "Top Rated unexpected error")
            throw e
        }
    }
}
