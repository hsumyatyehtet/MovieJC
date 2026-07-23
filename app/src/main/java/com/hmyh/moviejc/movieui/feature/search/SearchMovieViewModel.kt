package com.hmyh.moviejc.movieui.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmyh.moviejc.appbase.core.ListViewState
import com.hmyh.moviejc.domain.TwoParams
import com.hmyh.moviejc.domain.feature.search.model.MovieListVO
import com.hmyh.moviejc.domain.feature.search.usecase.GetSearchMovieUseCase
import com.hmyh.moviejc.network.extension.API_KEY_DATA
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor(
    private val getSearchMovieUseCase: GetSearchMovieUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _movieListState =
        MutableStateFlow<ListViewState<MovieListVO>>(ListViewState.Idle())
    val movieListState: StateFlow<ListViewState<MovieListVO>> = _movieListState.asStateFlow()

    private var searchJob: Job? = null

    fun onQueryChange(query: String) {
        _query.value = query
        searchJob?.cancel()

        if (query.isBlank()) {
            _movieListState.value = ListViewState.Idle()
            return
        }

        searchJob = viewModelScope.launch {
            delay(SEARCH_DEBOUNCE_MS)
            searchMovies(query.trim())
        }
    }

    private suspend fun searchMovies(query: String) {
        _movieListState.value = ListViewState.Loading()
        runCatching {
            val result = getSearchMovieUseCase.execute(
                TwoParams(one = query, two = API_KEY_DATA)
            )
            _movieListState.value = ListViewState.Success(
                result.movieList.orEmpty()
            )
        }.getOrElse {
            Timber.e(it)
            _movieListState.value = ListViewState.Error(
                it.message ?: "Failed to search movies"
            )
        }
    }

    companion object {
        private const val SEARCH_DEBOUNCE_MS = 400L
    }
}
