package com.hmyh.moviejc.network.feature.popular.di

import com.hmyh.moviejc.data.feature.popular.datasource.PopularMovieNetworkDataSource
import com.hmyh.moviejc.network.feature.popular.datasource_impl.PopularMovieNetworkDataSourceImpl
import com.hmyh.moviejc.network.feature.popular.service.PopularService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class PopularModule {

    @Binds
    abstract fun bindPopularMovieNetworkDataSource(
        popularMovieNetworkDataSourceImpl: PopularMovieNetworkDataSourceImpl
    ): PopularMovieNetworkDataSource

    companion object {
        @Provides
        fun providePopularService(retrofit: Retrofit): PopularService =
            retrofit.create(PopularService::class.java)
    }
}
