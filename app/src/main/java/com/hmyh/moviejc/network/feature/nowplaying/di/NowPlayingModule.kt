package com.hmyh.moviejc.network.feature.nowplaying.di

import com.hmyh.moviejc.data.feature.nowplaying.datasource.NowPlayingMovieNetworkDataSource
import com.hmyh.moviejc.network.feature.nowplaying.datasource_impl.NowPlayingMovieNetworkDataSourceImpl
import com.hmyh.moviejc.network.feature.nowplaying.service.NowPlayingService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class NowPlayingModule {

    @Binds
    abstract fun bindNowPlayingMovieNetworkDataSource(
        nowPlayingMovieNetworkDataSourceImpl: NowPlayingMovieNetworkDataSourceImpl
    ): NowPlayingMovieNetworkDataSource

    companion object {
        @Provides
        fun provideNowPlayingService(retrofit: Retrofit): NowPlayingService =
            retrofit.create(NowPlayingService::class.java)
    }
}
