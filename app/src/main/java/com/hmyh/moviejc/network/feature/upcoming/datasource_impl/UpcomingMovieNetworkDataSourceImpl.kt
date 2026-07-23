package com.hmyh.moviejc.network.feature.upcoming.datasource_impl

import com.hmyh.moviejc.data.feature.upcoming.datasource.UpcomingMovieNetworkDataSource
import com.hmyh.moviejc.data.feature.upcoming.model.UpcomingMoviePageEntity
import com.hmyh.moviejc.network.feature.upcoming.mapper.UpcomingMovieListNetworkMapper
import com.hmyh.moviejc.network.feature.upcoming.service.UpcomingService
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class UpcomingMovieNetworkDataSourceImpl @Inject constructor(
    private val service: UpcomingService,
    private val upcomingMovieListNetworkMapper: UpcomingMovieListNetworkMapper
) : UpcomingMovieNetworkDataSource {

    override suspend fun getUpcomingMovies(apiKey: String, page: Int): UpcomingMoviePageEntity {
        return try {
            val response = service.getUpcomingMovies(apiKey = apiKey, page = page)
            if (response.isSuccessful) {
                response.body()?.let(upcomingMovieListNetworkMapper::map)
                    ?: UpcomingMoviePageEntity()
            } else {
                Timber.e("Upcoming API error: ${response.code()} - ${response.message()}")
                UpcomingMoviePageEntity()
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: HttpException) {
            Timber.e(e, "Upcoming network error")
            UpcomingMoviePageEntity()
        } catch (e: Exception) {
            Timber.e(e, "Upcoming unexpected error")
            throw e
        }
    }
}
