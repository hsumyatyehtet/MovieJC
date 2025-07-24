package com.hmyh.moviejc.movieui.feature.home

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hmyh.moviejc.R
import com.hmyh.moviejc.appbase.core.ListViewState
import com.hmyh.moviejc.domain.feature.home.model.MovieVO
import com.hmyh.moviejc.domain.utils.movieDummyVO
import com.hmyh.moviejc.movieui.navagation.MovieScreens
import com.hmyh.moviejc.movieui.widget.MovieItem
import com.hmyh.moviejc.movieui.widget.MovieTitle
import com.hmyh.moviejc.network.extension.API_KEY_DATA
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMovieNew(
    navController: NavController,
    viewModel: HomeMovieViewModel = hiltViewModel()
) {

    //solution for status bar icon to white if topAppBar is dark
    val background = colorResource(id = R.color.background_color)
    val view = LocalView.current
    val activity = view.context as Activity
    SideEffect {
        val window = activity.window
        window.statusBarColor = background.toArgb()
        WindowInsetsControllerCompat(window, view).isAppearanceLightStatusBars = false
    }

    LaunchedEffect(Unit) {
        viewModel.getNowPlayingMoviesList(API_KEY_DATA)
    }
    val nowPlayingMovieListState by viewModel.movieListFlow.collectAsState()

    Scaffold(
        topBar = {
            HomeTopAppBar(
                onSearchClick = {
                    Timber.i("Search icon clicked")
                }
            )
        },
        containerColor = colorResource(id = R.color.background_color)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it),
            color = colorResource(id = R.color.background_color)
        ) {

            Column {
                when (nowPlayingMovieListState) {
                    is ListViewState.Success -> {
                        val movieList = (nowPlayingMovieListState as ListViewState.Success<MovieVO>).value
                        MainContent(navController, movieList)
                    }
                    is ListViewState.Loading -> {}
                    is ListViewState.Error -> {}
                    else -> {}
                }

            }
        }
    }
}

@Composable
fun MainContent(navController: NavController, movieList: List<MovieVO>) {

    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 8.dp)
    ){

        MovieTitle(title = "Now Playing Movie List") {
            Timber.i("See All clicked")
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 12.dp)
        ) {
            items(items = movieList) {
                MovieItem(it, onItemClick = {
                    navController.navigate(route =  MovieScreens.DetailMovie.name+"/$it")
                })
            }
        }

    }

}

@Preview
@Composable
fun MainContentPreview() {
    val sampleMovies = listOf(
        movieDummyVO,movieDummyVO,movieDummyVO,
        movieDummyVO
    )

    val navController = rememberNavController()

    MainContent(navController = navController, movieList = sampleMovies)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    onSearchClick: () -> Unit
) {

    TopAppBar(
        modifier = Modifier.fillMaxWidth().padding(start = 4.dp, end = 4.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.background_color)
        ),
        title = {
            Text(
                text = "Movie DB",
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

        },
        actions = {
            IconButton(
                onClick = onSearchClick,
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterVertically),
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
            }
        }
    )

}


@Preview(showBackground = true)
@Composable
fun HomeTopBarPreview() {
    HomeTopAppBar(onSearchClick = {})
}
