package com.hmyh.moviejc.network.feature.toprated.di

import com.hmyh.moviejc.data.feature.toprated.datasource.TopRatedMovieNetworkDataSource
import com.hmyh.moviejc.network.feature.toprated.datasource_impl.TopRatedMovieNetworkDataSourceImpl
import com.hmyh.moviejc.network.feature.toprated.service.TopRatedService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class TopRatedModule {

    @Binds
    abstract fun bindTopRatedMovieNetworkDataSource(
        topRatedMovieNetworkDataSourceImpl: TopRatedMovieNetworkDataSourceImpl
    ): TopRatedMovieNetworkDataSource

    companion object {
        @Provides
        fun provideTopRatedService(retrofit: Retrofit): TopRatedService =
            retrofit.create(TopRatedService::class.java)
    }
}
