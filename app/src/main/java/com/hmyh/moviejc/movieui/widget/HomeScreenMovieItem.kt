package com.hmyh.moviejc.movieui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.hmyh.moviejc.R
import com.hmyh.moviejc.domain.feature.common.domain.MovieDisplayable
import com.hmyh.moviejc.domain.feature.home.model.NowPlayingMovieVO
import com.hmyh.moviejc.domain.utils.movieDummyVO
import com.hmyh.moviejc.network.extension.PHOTO_PATH

@Composable
fun MovieItem(movie: MovieDisplayable, onItemClick: (Long) -> Unit) {

    val fullPosterPath = PHOTO_PATH + movie.posterPath

    Column(
        modifier = Modifier
            .width(120.dp)
            .clickable(
                onClick = {
                    onItemClick(movie.id)
                }
            ),
        horizontalAlignment = Alignment.Start,
    ) {

        Card(
            modifier = Modifier
                .height(180.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.colorTransparent)),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
            border = BorderStroke(
                width = 1.dp,
                color = colorResource(R.color.textColorSecondary)
            )
        ) {

            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(fullPosterPath)
                        .crossfade(true)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .build()
                ),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "Movie Poster",
                contentScale = ContentScale.Crop
            )

        }

    }
}

@Preview
@Composable
fun MovieItemPreview() {
    MovieItem(movie = movieDummyVO, onItemClick = {})
}