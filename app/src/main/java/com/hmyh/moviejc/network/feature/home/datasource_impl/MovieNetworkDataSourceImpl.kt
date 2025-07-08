package com.hmyh.moviejc.network.feature.home.datasource_impl

import android.util.Log
import com.hmyh.moviejc.data.feature.home.datasource.MovieNetworkDataSource
import com.hmyh.moviejc.data.feature.home.model.MovieEntity
import com.hmyh.moviejc.network.extension.getBody
import com.hmyh.moviejc.network.feature.home.mapper.MovieDataMapper
import com.hmyh.moviejc.network.feature.home.service.HomeService
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by H.M.Y.H on 2025/07/05
 */
class MovieNetworkDataSourceImpl @Inject constructor(
    private val service: HomeService,
    private val movieDataMapper: MovieDataMapper
) : MovieNetworkDataSource {

    override suspend fun getNowPlayingMovie(apiKey: String): List<MovieEntity> {
        return try {
            val response = service.loadNowPlayingMovies(apiKey)
            if (response.isSuccessful) {
                val pageResponse = response.body()
                pageResponse?.results?.map(movieDataMapper::map).orEmpty()
            } else {
                Timber.e("API error: ${response.code()} - ${response.message()}")
                emptyList()
            }
        } catch (e: HttpException) {
            Timber.e("Network error: ${e.message}")
            emptyList()
        } catch (e: Exception) {
            Timber.e("Unexpected error: ${e.message}")
            emptyList()
        }
    }

}