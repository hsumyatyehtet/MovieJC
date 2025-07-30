@file:OptIn(ExperimentalMaterial3Api::class)

package com.hmyh.moviejc.movieui.feature.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import com.hmyh.moviejc.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.hmyh.moviejc.appbase.core.ObjViewState
import com.hmyh.moviejc.domain.feature.moviedetail.model.MovieDetail
import com.hmyh.moviejc.domain.utils.movieDetailVO
import com.hmyh.moviejc.movieui.widget.CardWhiteBackground
import com.hmyh.moviejc.movieui.widget.FormattedDateText
import com.hmyh.moviejc.movieui.widget.MovieDetailToolbar
import com.hmyh.moviejc.movieui.widget.PosterItem
import com.hmyh.moviejc.movieui.widget.RatingCard
import com.hmyh.moviejc.movieui.widget.RoundedButton
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background_color))

    ) {

        when (val state = movieDetailState) {
            is ObjViewState.Success -> {

                MovieDetailToolbar(state.value.backDropPack, navController)
                MainLayout(state.value, navController)

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

@RequiresApi(Build.VERSION_CODES.O)
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
                    .padding(top = 130.dp)
                    .padding(horizontal = 16.dp)
            ) {

                DetailCard(movieDetail,navController)


                Text(
                    text = "Movie Title",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "This is a long scrollable description. ".repeat(50),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailCard(movieDetail: MovieDetail, navController: NavController) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(IntrinsicSize.Min)

    ) {

        CardWhiteBackground()
        RatingCard(voteAverage = movieDetail.voteAverage)

        Column (
            modifier = Modifier
                .padding(start = 20.dp, top = 0.dp, end = 20.dp)
                .align(Alignment.TopStart)

        ){
            MovieDetailHeader(movieDetail)
            ActionButtons()
        }

    }
}

@Composable
private fun ActionButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        RoundedButton(
            modifier = Modifier.weight(1f),
            backgroundColor = R.color.colorPlayButtonBackground
        )

        Spacer(modifier = Modifier.width(12.dp))

        RoundedButton(
            modifier = Modifier.weight(1f),
            backgroundColor = R.color.white
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MovieDetailHeader(movieDetail: MovieDetail) {
    Row(
        modifier = Modifier.offset(y = (-30).dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        PosterItem(movieDetail.posterPath)

        Spacer(modifier = Modifier.width(12.dp))

        val date = FormattedDateText(movieDetail.releaseDate)

        Column {
            Text(
                modifier = Modifier
                    .padding(top = 40.dp),
                text = movieDetail.title,
                color = colorResource(id = R.color.textColorPrimary),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp, end = 20.dp),
                text = date,
                color = colorResource(id = R.color.textColorPrimary),
                fontSize = 12.sp,
                fontWeight = FontWeight.W500
            )
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DetailCardPreview() {
    DetailCard(movieDetail = movieDetailVO, navController = rememberNavController())
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(backgroundColor = 0x0e1f30, showBackground = true)
@Composable
fun MainLayoutPreview() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        MainLayout(movieDetail = movieDetailVO, navController = rememberNavController())
    }
}

