package com.hmyh.moviejc.network.feature.upcoming.di

import com.hmyh.moviejc.data.feature.upcoming.datasource.UpcomingMovieNetworkDataSource
import com.hmyh.moviejc.network.feature.upcoming.datasource_impl.UpcomingMovieNetworkDataSourceImpl
import com.hmyh.moviejc.network.feature.upcoming.service.UpcomingService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class UpcomingModule {

    @Binds
    abstract fun bindUpcomingMovieNetworkDataSource(
        upcomingMovieNetworkDataSourceImpl: UpcomingMovieNetworkDataSourceImpl
    ): UpcomingMovieNetworkDataSource

    companion object {
        @Provides
        fun provideUpcomingService(retrofit: Retrofit): UpcomingService =
            retrofit.create(UpcomingService::class.java)
    }
}
