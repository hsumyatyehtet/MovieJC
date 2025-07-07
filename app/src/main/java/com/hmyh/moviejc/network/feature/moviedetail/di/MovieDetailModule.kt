package com.hmyh.moviejc.network.feature.moviedetail.di

import com.hmyh.moviejc.data.feature.moviedetail.datasoruce.MovieDetailNetworkDataSource
import com.hmyh.moviejc.network.feature.moviedetail.datasource_impl.MovieDetailNetworkDataSourceImpl
import com.hmyh.moviejc.network.feature.moviedetail.service.MovieDetailService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieDetailModule {

    @Binds
    abstract fun bindMovieDetailNetworkDataSource(movieDetailNetworkDataSourceImpl: MovieDetailNetworkDataSourceImpl): MovieDetailNetworkDataSource

    companion object{
        @Provides
        fun provideMovieDetailService(retrofit: Retrofit): MovieDetailService =
            retrofit.create(MovieDetailService::class.java)
    }

}