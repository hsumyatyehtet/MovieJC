package com.hmyh.moviejc.movieui.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmyh.moviejc.appbase.core.ListViewState
import com.hmyh.moviejc.domain.ThreeParams
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

    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore.asStateFlow()

    private val _canLoadMore = MutableStateFlow(false)
    val canLoadMore: StateFlow<Boolean> = _canLoadMore.asStateFlow()

    private var searchJob: Job? = null
    private var loadMoreJob: Job? = null
    private var currentPage = 0
    private var totalPages = 1
    private val accumulatedMovies = mutableListOf<MovieListVO>()

    fun onQueryChange(query: String) {
        _query.value = query
        searchJob?.cancel()
        loadMoreJob?.cancel()
        _isLoadingMore.value = false
        _canLoadMore.value = false

        if (query.isBlank()) {
            resetPagination()
            _movieListState.value = ListViewState.Idle()
            return
        }

        searchJob = viewModelScope.launch {
            delay(SEARCH_DEBOUNCE_MS)
            searchMovies(query = query.trim(), page = FIRST_PAGE, append = false)
        }
    }

    fun loadMore() {
        if (_isLoadingMore.value || !_canLoadMore.value) return

        val query = _query.value.trim()
        if (query.isBlank() || _movieListState.value !is ListViewState.Success) return

        val nextPage = currentPage + 1
        _isLoadingMore.value = true
        _canLoadMore.value = false

        loadMoreJob = viewModelScope.launch {
            searchMovies(query = query, page = nextPage, append = true)
        }
    }

    private suspend fun searchMovies(query: String, page: Int, append: Boolean) {
        if (!append) {
            resetPagination()
            _isLoadingMore.value = false
            _canLoadMore.value = false
            _movieListState.value = ListViewState.Loading()
        }

        try {
            val result = getSearchMovieUseCase.execute(
                ThreeParams(one = query, two = API_KEY_DATA, three = page)
            )
            currentPage = result.page?.toInt() ?: page
            totalPages = (result.totalPages ?: 1L).toInt().coerceAtLeast(1)

            val newMovies = result.movieList.orEmpty()
            if (append) {
                val existingIds = accumulatedMovies.mapTo(HashSet()) { it.id }
                accumulatedMovies.addAll(newMovies.filter { it.id !in existingIds })
            } else {
                accumulatedMovies.clear()
                accumulatedMovies.addAll(newMovies)
            }

            _movieListState.value = ListViewState.Success(accumulatedMovies.toList())
            _canLoadMore.value = currentPage < totalPages
        } catch (e: Exception) {
            Timber.e(e)
            if (!append) {
                _movieListState.value = ListViewState.Error(
                    e.message ?: "Failed to search movies"
                )
                _canLoadMore.value = false
            } else {
                _canLoadMore.value = currentPage < totalPages
            }
        } finally {
            if (append) {
                _isLoadingMore.value = false
            }
        }
    }

    private fun resetPagination() {
        currentPage = 0
        totalPages = 1
        accumulatedMovies.clear()
    }

    companion object {
        private const val SEARCH_DEBOUNCE_MS = 400L
        private const val FIRST_PAGE = 1
    }
}
