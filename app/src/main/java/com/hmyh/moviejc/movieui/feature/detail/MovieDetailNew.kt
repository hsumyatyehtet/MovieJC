@file:OptIn(ExperimentalMaterial3Api::class)

package com.hmyh.moviejc.movieui.feature.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import com.hmyh.moviejc.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.hmyh.moviejc.appbase.core.ObjViewState
import com.hmyh.moviejc.domain.feature.moviedetail.model.MovieDetail
import com.hmyh.moviejc.domain.utils.movieDetailVO
import com.hmyh.moviejc.network.extension.API_KEY_DATA
import com.hmyh.moviejc.network.extension.PHOTO_PATH
import timber.log.Timber

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailNew(
    navController: NavController,
    id: Long,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(id) {
        viewModel.getMovieDetail(id, API_KEY_DATA)
    }

    val movieDetailState by viewModel.movieDetailFlow.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        // 1. Background Image (bottom-most layer)

        when (val state = movieDetailState) {
            is ObjViewState.Success -> {

                MovieDetailToolbar(state.value.backDropPack,navController)
                MainLayout(state.value,navController)

                Timber.i("movieDetail ${state.value.title}")
            }

            is ObjViewState.Loading -> {

            }

            is ObjViewState.Error -> {
                Timber.e(state.errorMessage)
            }

            else -> {

            }
        }

    }


}

@Composable
fun MainLayout(movieDetail: MovieDetail, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 250.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Movie Title",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "This is a long scrollable description. ".repeat(50),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun MovieDetailToolbar(posterPath: String,navController: NavController) {

    val fullPosterPath = PHOTO_PATH + posterPath

    Box {
        Image(
            painter = rememberAsyncImagePainter(model = fullPosterPath),
            contentDescription = "detail",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .align(Alignment.Center)
        )

        // 2. Gradient Overlay (middle layer)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .shadow(elevation = 2.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            colorResource(R.color.colorMovieDetailTransparent),
                            Color.Transparent
                        )
                    )
                )

        )

        // 3. TopAppBar (top-most layer)
        TopAppBar(
            title = { Text("") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding(),
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.padding(8.dp).clickable {
                        navController.popBackStack()
                    },
                    tint = Color.White
                )
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ToolBarPreview(){
    MovieDetailToolbar(posterPath = movieDetailVO.posterPath, navController = rememberNavController())
}

