package com.hmyh.moviejc.movieui.feature.search

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hmyh.moviejc.R
import com.hmyh.moviejc.appbase.core.ListViewState
import com.hmyh.moviejc.domain.feature.search.model.MovieListVO
import com.hmyh.moviejc.domain.utils.searchMovieDummyList
import com.hmyh.moviejc.movieui.navagation.MovieScreens
import com.hmyh.moviejc.movieui.widget.MovieItem
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

@Composable
fun SearchMovie(
    navController: NavController,
    viewModel: SearchMovieViewModel = hiltViewModel()
) {
    val background = colorResource(id = R.color.background_color)
    val view = LocalView.current
    val activity = view.context as Activity
    SideEffect {
        val window = activity.window
        window.statusBarColor = background.toArgb()
        WindowInsetsControllerCompat(window, view).isAppearanceLightStatusBars = false
    }

    val query by viewModel.query.collectAsState()
    val movieListState by viewModel.movieListState.collectAsState()
    val isLoadingMore by viewModel.isLoadingMore.collectAsState()
    val canLoadMore by viewModel.canLoadMore.collectAsState()

    Scaffold(
        topBar = {
            SearchTopAppBar(
                query = query,
                onQueryChange = viewModel::onQueryChange,
                onBackClick = { navController.popBackStack() }
            )
        },
        containerColor = colorResource(id = R.color.background_color)
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = colorResource(id = R.color.background_color)
        ) {
            SearchContent(
                query = query,
                movieListState = movieListState,
                isLoadingMore = isLoadingMore,
                canLoadMore = canLoadMore,
                onLoadMore = viewModel::loadMore,
                onItemClick = { movieId ->
                    navController.navigate(MovieScreens.DetailMovie.name + "/$movieId")
                }
            )
        }
    }
}

@Composable
fun SearchContent(
    query: String,
    movieListState: ListViewState<MovieListVO>,
    isLoadingMore: Boolean,
    canLoadMore: Boolean,
    onLoadMore: () -> Unit,
    onItemClick: (Long) -> Unit
) {
    when (movieListState) {
        is ListViewState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = colorResource(id = R.color.colorPlayButtonBackground)
                )
            }
        }

        is ListViewState.Success -> {
            val movieList = movieListState.value
            if (movieList.isEmpty()) {
                SearchEmptyMessage(text = "No movies found")
            } else {
                SearchMovieGrid(
                    movieList = movieList,
                    isLoadingMore = isLoadingMore,
                    canLoadMore = canLoadMore,
                    onLoadMore = onLoadMore,
                    onItemClick = onItemClick
                )
            }
        }

        is ListViewState.Error -> {
            SearchEmptyMessage(text = movieListState.errorMessage)
        }

        else -> {
            if (query.isBlank()) {
                SearchEmptyMessage(text = "Search for movies")
            } else {
                SearchEmptyMessage(text = "No movies found")
            }
        }
    }
}

@Composable
private fun SearchMovieGrid(
    movieList: List<MovieListVO>,
    isLoadingMore: Boolean,
    canLoadMore: Boolean,
    onLoadMore: () -> Unit,
    onItemClick: (Long) -> Unit
) {
    val gridState = rememberLazyGridState()

    LaunchedEffect(gridState, movieList.size, canLoadMore, isLoadingMore) {
        snapshotFlow {
            val layoutInfo = gridState.layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            val lastVisibleIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
            Triple(lastVisibleIndex, totalItems, canLoadMore && !isLoadingMore)
        }
            .map { (lastVisibleIndex, totalItems, canTrigger) ->
                canTrigger &&
                    totalItems > 0 &&
                    lastVisibleIndex >= 0 &&
                    lastVisibleIndex >= totalItems - LOAD_MORE_THRESHOLD
            }
            .distinctUntilChanged()
            .filter { it }
            .collect { onLoadMore() }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            state = gridState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = movieList, key = { it.id }) { movie ->
                SearchMovieItem(
                    movie = movie,
                    onItemClick = onItemClick
                )
            }
        }

        if (isLoadingMore) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.35f))
                    .pointerInput(Unit) {
                        awaitPointerEventScope {
                            while (true) {
                                awaitPointerEvent()
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(40.dp),
                    strokeWidth = 3.dp,
                    color = colorResource(id = R.color.colorPlayButtonBackground)
                )
            }
        }
    }
}

@Composable
private fun SearchEmptyMessage(text: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 16.sp
        )
    }
}

@Composable
fun SearchMovieItem(
    movie: MovieListVO,
    onItemClick: (Long) -> Unit
) {
    Column(horizontalAlignment = Alignment.Start) {
        MovieItem(
            movie = movie,
            onItemClick = onItemClick,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = movie.title,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 6.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun SearchTopAppBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(start = 4.dp, end = 16.dp, top = 4.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .padding(start = 4.dp),
            singleLine = true,
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 14.sp
            ),
            cursorBrush = SolidColor(Color.White),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = { focusManager.clearFocus() }
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = colorResource(id = R.color.colorGenreBackground),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = colorResource(id = R.color.colorBlueGrey300),
                        modifier = Modifier.size(18.dp)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (query.isEmpty()) {
                            Text(
                                text = "Search movies...",
                                color = colorResource(id = R.color.colorBlueGrey400),
                                fontSize = 14.sp
                            )
                        }
                        innerTextField()
                    }
                    if (query.isNotEmpty()) {
                        IconButton(
                            onClick = { onQueryChange("") },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear",
                                tint = colorResource(id = R.color.colorBlueGrey300),
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchTopAppBarPreview() {
    SearchTopAppBar(
        query = "Sample",
        onQueryChange = {},
        onBackClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun SearchContentPreview() {
    Surface(color = colorResource(id = R.color.background_color)) {
        SearchContent(
            query = "Inception",
            movieListState = ListViewState.Success(searchMovieDummyList),
            isLoadingMore = false,
            canLoadMore = true,
            onLoadMore = {},
            onItemClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchMoviePreview() {
    val navController = rememberNavController()
    Surface(color = colorResource(id = R.color.background_color)) {
        SearchContent(
            query = "Movie",
            movieListState = ListViewState.Success(searchMovieDummyList),
            isLoadingMore = true,
            canLoadMore = false,
            onLoadMore = {},
            onItemClick = {
                navController.navigate(MovieScreens.DetailMovie.name + "/$it")
            }
        )
    }
}

private const val LOAD_MORE_THRESHOLD = 3
