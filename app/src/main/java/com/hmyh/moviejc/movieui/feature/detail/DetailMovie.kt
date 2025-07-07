@file:OptIn(ExperimentalMaterial3Api::class)

package com.hmyh.moviejc.movieui.feature.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hmyh.moviejc.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.hmyh.moviejc.movieui.widget.InfoRow
import androidx.hilt.navigation.compose.hiltViewModel
import com.hmyh.moviejc.appbase.core.ObjViewState
import com.hmyh.moviejc.domain.feature.moviedetail.model.MovieDetail
import com.hmyh.moviejc.movieui.widget.InfoRowDate
import com.hmyh.moviejc.movieui.widget.InfoRowHour
import com.hmyh.moviejc.network.extension.API_KEY_DATA
import com.hmyh.moviejc.network.extension.PHOTO_PATH
import timber.log.Timber
import java.nio.file.WatchEvent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailMovie(
    navController: NavController,
    id: Long,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(id) {
        viewModel.getMovieDetail(id, API_KEY_DATA)
    }

    val movieDetailState by viewModel.movieDetailFlow.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                title = {

                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.padding(8.dp).clickable {
                            navController.popBackStack()
                        },
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            )
        }
    ) {paddingValues->
        Surface(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {

            when (val state = movieDetailState) {
                is ObjViewState.Success -> {
                    MovieDetailContent(movieDetail = state.value)
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

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MovieDetailContent(movieDetail: MovieDetail) {

    val fullPosterPath = PHOTO_PATH + movieDetail.posterPath

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp)
    ) {

        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .size(300.dp)
                    .padding(start = 50.dp, end = 50.dp, bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = fullPosterPath),
                contentDescription = "detail",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        InfoRow(label = "Original Title:", value = movieDetail.originalTitle)
        InfoRowDate(label = "Release on:", value = movieDetail.releaseDate)
        InfoRowHour(label = "Lasts:", value = movieDetail.runtime)

        Text(
            text = movieDetail.overView,
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 4.dp)
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(movieDetail.genreList) {index,it->
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 16.dp, end = if (index != movieDetail.genreList.lastIndex) 8.dp else 0.dp)
                        .wrapContentSize()
                        .background(
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(4.dp)
                        )
                    ,
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it?.name.toString(),
                        color = Color.White,
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 16.dp, end = 16.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

    }
}