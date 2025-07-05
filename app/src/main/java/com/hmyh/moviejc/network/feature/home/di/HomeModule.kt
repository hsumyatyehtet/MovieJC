package com.hmyh.moviejc.network.feature.home.di

import com.hmyh.moviejc.data.feature.home.datasource.MovieNetworkDataSource
import com.hmyh.moviejc.network.feature.home.datasource_impl.MovieNetworkDataSourceImpl
import com.hmyh.moviejc.network.feature.home.service.HomeService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeModule {

    @Binds
    abstract fun bindMovieNetworkDataSource(networkDataSourceImpl: MovieNetworkDataSourceImpl): MovieNetworkDataSource

    companion object{
        @Provides
        fun provideHomeService(retrofit: Retrofit): HomeService =
            retrofit.create(HomeService::class.java)

    }

}