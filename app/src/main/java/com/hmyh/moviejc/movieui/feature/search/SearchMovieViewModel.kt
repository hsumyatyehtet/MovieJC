package com.hmyh.moviejc.movieui.feature.search

import androidx.lifecycle.ViewModel
import com.hmyh.moviejc.domain.feature.home.model.NowPlayingMovieVO
import com.hmyh.moviejc.domain.utils.searchMovieDummyList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor() : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _movieList = MutableStateFlow(searchMovieDummyList)
    val movieList: StateFlow<List<NowPlayingMovieVO>> = _movieList.asStateFlow()

    fun onQueryChange(query: String) {
        _query.value = query
        _movieList.value = if (query.isBlank()) {
            searchMovieDummyList
        } else {
            searchMovieDummyList.filter {
                it.title.contains(query, ignoreCase = true) ||
                    it.originalTitle.contains(query, ignoreCase = true)
            }
        }
    }
}
