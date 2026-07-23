package com.hmyh.moviejc.movieui.feature.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmyh.moviejc.appbase.core.ListViewState
import com.hmyh.moviejc.domain.TwoParams
import com.hmyh.moviejc.domain.feature.popular.model.PopularMovieItemVO
import com.hmyh.moviejc.domain.feature.popular.usecase.GetPopularMovieListUseCase
import com.hmyh.moviejc.network.extension.API_KEY_DATA
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase
) : ViewModel() {

    private val _movieListState =
        MutableStateFlow<ListViewState<PopularMovieItemVO>>(ListViewState.Idle())
    val movieListState: StateFlow<ListViewState<PopularMovieItemVO>> =
        _movieListState.asStateFlow()

    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore.asStateFlow()

    private val _canLoadMore = MutableStateFlow(false)
    val canLoadMore: StateFlow<Boolean> = _canLoadMore.asStateFlow()

    private var loadMoreJob: Job? = null
    private var currentPage = 0
    private var totalPages = 1
    private val accumulatedMovies = mutableListOf<PopularMovieItemVO>()

    init {
        viewModelScope.launch {
            loadMovies(page = FIRST_PAGE, append = false)
        }
    }

    fun loadMore() {
        if (_isLoadingMore.value || !_canLoadMore.value) return
        if (_movieListState.value !is ListViewState.Success) return

        val nextPage = currentPage + 1
        _isLoadingMore.value = true
        _canLoadMore.value = false

        loadMoreJob = viewModelScope.launch {
            loadMovies(page = nextPage, append = true)
        }
    }

    fun retry() {
        loadMoreJob?.cancel()
        _isLoadingMore.value = false
        viewModelScope.launch {
            loadMovies(page = FIRST_PAGE, append = false)
        }
    }

    private suspend fun loadMovies(page: Int, append: Boolean) {
        if (!append) {
            resetPagination()
            _isLoadingMore.value = false
            _canLoadMore.value = false
            _movieListState.value = ListViewState.Loading()
        }

        try {
            val result = getPopularMovieListUseCase.execute(
                TwoParams(one = API_KEY_DATA, two = page)
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
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Timber.e(e)
            if (!append) {
                _movieListState.value = ListViewState.Error(
                    e.message ?: "Failed to load popular movies"
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
        private const val FIRST_PAGE = 1
    }
}
