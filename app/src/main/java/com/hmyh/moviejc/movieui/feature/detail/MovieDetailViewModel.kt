package com.hmyh.moviejc.movieui.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmyh.moviejc.appbase.core.ListViewState
import com.hmyh.moviejc.appbase.core.ObjViewState
import com.hmyh.moviejc.domain.TwoParams
import com.hmyh.moviejc.domain.feature.moviedetail.model.MovieDetail
import com.hmyh.moviejc.domain.feature.moviedetail.usercase.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: GetMovieDetailUseCase
): ViewModel() {

    private val _movieDetailFlow: MutableStateFlow<ObjViewState<MovieDetail>> =
        MutableStateFlow(ObjViewState.Idle())
    val movieDetailFlow = _movieDetailFlow.asStateFlow()

    fun getMovieDetail(movieId: Long,apkKey: String){

        viewModelScope.launch {
            runCatching {
                val params = TwoParams(movieId,apkKey)
                val data = movieDetailUseCase.execute(params)
                _movieDetailFlow.value = ObjViewState.Success(data)
            }.getOrElse {
                Timber.e(it)
                // _movieListFlow.value = ListViewState.Error(exception.map(it))
            }
        }

    }

}