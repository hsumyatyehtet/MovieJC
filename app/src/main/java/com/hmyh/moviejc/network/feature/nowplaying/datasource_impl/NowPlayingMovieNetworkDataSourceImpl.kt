package com.hmyh.moviejc.network.feature.nowplaying.datasource_impl

import com.hmyh.moviejc.data.feature.nowplaying.datasource.NowPlayingMovieNetworkDataSource
import com.hmyh.moviejc.data.feature.nowplaying.model.NowPlayingMoviePageEntity
import com.hmyh.moviejc.network.feature.nowplaying.mapper.NowPlayingMovieNetworkMapper
import com.hmyh.moviejc.network.feature.nowplaying.service.NowPlayingService
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class NowPlayingMovieNetworkDataSourceImpl @Inject constructor(
    private val service: NowPlayingService,
    private val nowPlayingMovieNetworkMapper: NowPlayingMovieNetworkMapper
) : NowPlayingMovieNetworkDataSource {

    override suspend fun getNowPlayingMovies(apiKey: String, page: Int): NowPlayingMoviePageEntity {
        return try {
            val response = service.getNowPlayingMovies(apiKey = apiKey, page = page)
            if (response.isSuccessful) {
                response.body()?.let(nowPlayingMovieNetworkMapper::map)
                    ?: NowPlayingMoviePageEntity()
            } else {
                Timber.e("Now Playing API error: ${response.code()} - ${response.message()}")
                NowPlayingMoviePageEntity()
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: HttpException) {
            Timber.e(e, "Now Playing network error")
            NowPlayingMoviePageEntity()
        } catch (e: Exception) {
            Timber.e(e, "Now Playing unexpected error")
            throw e
        }
    }
}
