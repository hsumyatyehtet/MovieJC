package com.hmyh.moviejc.movieui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmyh.moviejc.appbase.core.ListViewState
import com.hmyh.moviejc.domain.feature.home.model.Movie
import com.hmyh.moviejc.domain.feature.home.repository.MovieRepository
import com.hmyh.moviejc.domain.feature.home.usecase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeMovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieUseCase: GetMovieUseCase
) : ViewModel() {

    private val _movieListFlow: MutableStateFlow<ListViewState<Movie>> =
        MutableStateFlow(ListViewState.Idle())
    val movieListFlow = _movieListFlow.asStateFlow()

    fun getNowPlayingMoviesList(apkKey: String) {
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
}