package com.hmyh.moviejc.movieui.feature.popular

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
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
import com.hmyh.moviejc.domain.feature.popular.model.PopularMovieItemVO
import com.hmyh.moviejc.movieui.navagation.MovieScreens
import com.hmyh.moviejc.movieui.widget.MovieItem
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

@Composable
fun PopularMovies(
    navController: NavController,
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {
    val background = colorResource(id = R.color.background_color)
    val view = LocalView.current
    val activity = view.context as Activity
    SideEffect {
        val window = activity.window
        window.statusBarColor = background.toArgb()
        WindowInsetsControllerCompat(window, view).isAppearanceLightStatusBars = false
    }

    val movieListState by viewModel.movieListState.collectAsState()
    val isLoadingMore by viewModel.isLoadingMore.collectAsState()
    val canLoadMore by viewModel.canLoadMore.collectAsState()

    Scaffold(
        topBar = {
            PopularTopAppBar(
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
            PopularContent(
                movieListState = movieListState,
                isLoadingMore = isLoadingMore,
                canLoadMore = canLoadMore,
                onLoadMore = viewModel::loadMore,
                onRetry = viewModel::retry,
                onItemClick = { movieId ->
                    navController.navigate(MovieScreens.DetailMovie.name + "/$movieId")
                }
            )
        }
    }
}

@Composable
fun PopularContent(
    movieListState: ListViewState<PopularMovieItemVO>,
    isLoadingMore: Boolean,
    canLoadMore: Boolean,
    onLoadMore: () -> Unit,
    onRetry: () -> Unit,
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
                PopularEmptyMessage(text = "No movies found")
            } else {
                PopularMovieGrid(
                    movieList = movieList,
                    isLoadingMore = isLoadingMore,
                    canLoadMore = canLoadMore,
                    onLoadMore = onLoadMore,
                    onItemClick = onItemClick
                )
            }
        }

        is ListViewState.Error -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = onRetry),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = movieListState.errorMessage,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp
                )
                Text(
                    text = "Tap to retry",
                    color = colorResource(id = R.color.colorPlayButtonBackground),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }

        else -> {
            PopularEmptyMessage(text = "No movies found")
        }
    }
}

@Composable
private fun PopularMovieGrid(
    movieList: List<PopularMovieItemVO>,
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
                PopularMovieGridItem(
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
private fun PopularEmptyMessage(text: String) {
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
fun PopularMovieGridItem(
    movie: PopularMovieItemVO,
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
fun PopularTopAppBar(
    onBackClick: () -> Unit
) {
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
        Text(
            text = "Popular Movies",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PopularTopAppBarPreview() {
    PopularTopAppBar(onBackClick = {})
}

@Preview(showBackground = true)
@Composable
fun PopularContentPreview() {
    val sampleMovies = listOf(
        PopularMovieItemVO(
            id = 1,
            originalTitle = "Sample",
            popularity = 10f,
            posterPath = "",
            title = "Sample Movie",
            releaseDate = "2024-01-01",
            voteCount = 100
        )
    )
    Surface(color = colorResource(id = R.color.background_color)) {
        PopularContent(
            movieListState = ListViewState.Success(sampleMovies),
            isLoadingMore = false,
            canLoadMore = true,
            onLoadMore = {},
            onRetry = {},
            onItemClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PopularMoviesPreview() {
    val navController = rememberNavController()
    val sampleMovies = listOf(
        PopularMovieItemVO(
            id = 1,
            originalTitle = "Sample",
            popularity = 10f,
            posterPath = "",
            title = "Sample Movie",
            releaseDate = "2024-01-01",
            voteCount = 100
        )
    )
    Surface(color = colorResource(id = R.color.background_color)) {
        PopularContent(
            movieListState = ListViewState.Success(sampleMovies),
            isLoadingMore = true,
            canLoadMore = false,
            onLoadMore = {},
            onRetry = {},
            onItemClick = {
                navController.navigate(MovieScreens.DetailMovie.name + "/$it")
            }
        )
    }
}

private const val LOAD_MORE_THRESHOLD = 3
