package com.hmyh.moviejc.network.feature.search.di

import com.hmyh.moviejc.data.feature.search.datasource.SearchMovieNetworkDataSource
import com.hmyh.moviejc.network.feature.search.datasource_impl.SearchMovieNetworkDataSourceImpl
import com.hmyh.moviejc.network.feature.search.service.SearchService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchModule {

    @Binds
    abstract fun bindSearchMovieNetworkDataSource(
        searchMovieNetworkDataSourceImpl: SearchMovieNetworkDataSourceImpl
    ): SearchMovieNetworkDataSource

    companion object {
        @Provides
        fun provideSearchService(retrofit: Retrofit): SearchService =
            retrofit.create(SearchService::class.java)
    }
}
