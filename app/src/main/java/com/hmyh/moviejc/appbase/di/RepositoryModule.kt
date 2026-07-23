package com.hmyh.moviejc.appbase.di

import com.hmyh.moviejc.data.feature.home.repository_impl.MovieRepositoryImpl
import com.hmyh.moviejc.data.feature.moviedetail.repository_impl.MovieDetailRepositoryImpl
import com.hmyh.moviejc.data.feature.nowplaying.repository_impl.NowPlayingMovieRepositoryImpl
import com.hmyh.moviejc.data.feature.popular.repository_impl.PopularMovieRepositoryImpl
import com.hmyh.moviejc.data.feature.search.repository_impl.SearchMovieRepositoryImpl
import com.hmyh.moviejc.data.feature.toprated.repository_impl.TopRatedMovieRepositoryImpl
import com.hmyh.moviejc.data.feature.upcoming.repository_impl.UpcomingMovieRepositoryImpl
import com.hmyh.moviejc.domain.feature.home.repository.MovieRepository
import com.hmyh.moviejc.domain.feature.moviedetail.repository.MovieDetailRepository
import com.hmyh.moviejc.domain.feature.nowplaying.repository.NowPlayingMovieRepository
import com.hmyh.moviejc.domain.feature.popular.repository.PopularMovieRepository
import com.hmyh.moviejc.domain.feature.search.repository.SearchMovieRepository
import com.hmyh.moviejc.domain.feature.toprated.repository.TopRatedMovieRepository
import com.hmyh.moviejc.domain.feature.upcoming.repository.UpcomingMovieRepository
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

    @Binds
    abstract fun bindNowPlayingMovieRepository(
        nowPlayingMovieRepositoryImpl: NowPlayingMovieRepositoryImpl
    ): NowPlayingMovieRepository

    @Binds
    abstract fun bindPopularMovieRepository(
        popularMovieRepositoryImpl: PopularMovieRepositoryImpl
    ): PopularMovieRepository

    @Binds
    abstract fun bindTopRatedMovieRepository(
        topRatedMovieRepositoryImpl: TopRatedMovieRepositoryImpl
    ): TopRatedMovieRepository

    @Binds
    abstract fun bindUpcomingMovieRepository(
        upcomingMovieRepositoryImpl: UpcomingMovieRepositoryImpl
    ): UpcomingMovieRepository

}
