package com.hmyh.moviejc.appbase.di

import com.hmyh.moviejc.data.feature.home.repository_impl.MovieRepositoryImpl
import com.hmyh.moviejc.domain.feature.home.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository

}