package com.hmyh.moviejc.appbase.di

import com.hmyh.moviejc.data.feature.home.repository_impl.MovieRepositoryImpl
import com.hmyh.moviejc.data.feature.moviedetail.repository_impl.MovieDetailRepositoryImpl
import com.hmyh.moviejc.data.feature.search.repository_impl.SearchMovieRepositoryImpl
import com.hmyh.moviejc.domain.feature.home.repository.MovieRepository
import com.hmyh.moviejc.domain.feature.moviedetail.repository.MovieDetailRepository
import com.hmyh.moviejc.domain.feature.search.repository.SearchMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindMovieDetailRepository(movieDetailRepositoryImpl: MovieDetailRepositoryImpl): MovieDetailRepository

    @Binds
    abstract fun bindSearchMovieRepository(
        searchMovieRepositoryImpl: SearchMovieRepositoryImpl
    ): SearchMovieRepository

}