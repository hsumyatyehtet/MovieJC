package com.hmyh.moviejc.movieui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmyh.moviejc.appbase.core.ListViewState
import com.hmyh.moviejc.domain.feature.home.model.NowPlayingMovieVO
import com.hmyh.moviejc.domain.feature.home.model.PopularMovieVO
import com.hmyh.moviejc.domain.feature.home.model.TopRatedMovieVO
import com.hmyh.moviejc.domain.feature.home.model.UpcomingMovieVO
import com.hmyh.moviejc.domain.feature.home.repository.MovieRepository
import com.hmyh.moviejc.domain.feature.home.usecase.GetNowPlayingMovieUseCase
import com.hmyh.moviejc.domain.feature.home.usecase.GetPopularMovieUseCase
import com.hmyh.moviejc.domain.feature.home.usecase.GetTopRatedMovieUseCase
import com.hmyh.moviejc.domain.feature.home.usecase.GetUpcomingMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeMovieViewModel @Inject constructor(
    private val movieUseCase: GetNowPlayingMovieUseCase,
    private val popularMovieUseCase: GetPopularMovieUseCase,
    private val topRatedMovieUseCase: GetTopRatedMovieUseCase,
    private val upComingMovieUseCase: GetUpcomingMovieUseCase
) : ViewModel() {

    private val _movieListFlow: MutableStateFlow<ListViewState<NowPlayingMovieVO>> =
        MutableStateFlow(ListViewState.Idle())
    val movieListFlow = _movieListFlow.asStateFlow()

    private val _popularMovieListFlow: MutableStateFlow<ListViewState<PopularMovieVO>> =
        MutableStateFlow(ListViewState.Idle())
    val popularMovieListFlow = _popularMovieListFlow.asStateFlow()

    private val _topRatedMovieListFlow: MutableStateFlow<ListViewState<TopRatedMovieVO>> =
        MutableStateFlow(ListViewState.Idle())
    val topRatedMovieListFlow = _topRatedMovieListFlow.asStateFlow()

    private val _upComingMovieListFlow: MutableStateFlow<ListViewState<UpcomingMovieVO>> =
        MutableStateFlow(ListViewState.Idle())
    val upComingMovieListFlow = _upComingMovieListFlow.asStateFlow()

    suspend fun getNowPlayingMoviesList(apkKey: String) {
        _movieListFlow.value = ListViewState.Loading()
        viewModelScope.launch {
            runCatching {
                val data = movieUseCase.execute(apkKey)
                _movieListFlow.value = ListViewState.Success(data)
            }.getOrElse {
                Timber.e(it)
                // _movieListFlow.value = ListViewState.Error(exception.map(it))
            }
        }
    }

    suspend fun getPopularMovieList(apiKey: String){
        _popularMovieListFlow.value = ListViewState.Loading()
        viewModelScope.launch {
            runCatching {
                val data = popularMovieUseCase.execute(apiKey)
                _popularMovieListFlow.value = ListViewState.Success(data)
            }.getOrElse {
                Timber.e(it)
            }
        }
    }

    suspend fun getTopRatedMovieList(apiKey: String){
        _topRatedMovieListFlow.value = ListViewState.Loading()
        viewModelScope.launch {
            runCatching {
                val data = topRatedMovieUseCase.execute(apiKey)
                _topRatedMovieListFlow.value = ListViewState.Success(data)
            }.getOrElse {
                Timber.e(it)
            }
        }
    }

    suspend fun getUpcomingMovieList(apiKey: String){
        _upComingMovieListFlow.value = ListViewState.Loading()
        viewModelScope.launch {
            runCatching {
                val data = upComingMovieUseCase.execute(apiKey)
                _upComingMovieListFlow.value = ListViewState.Success(data)
            }.getOrElse {
                Timber.e(it)
            }
        }
    }

}