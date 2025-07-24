package com.hmyh.moviejc.movieui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.hmyh.moviejc.R
import com.hmyh.moviejc.domain.feature.home.model.MovieVO
import com.hmyh.moviejc.domain.utils.movieDummyVO
import com.hmyh.moviejc.network.extension.PHOTO_PATH

@Composable
fun MovieItem(movie: MovieVO) {

    val fullPosterPath = PHOTO_PATH + movie.posterPath

    Column(
        modifier = Modifier.width(150.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Card(
            modifier = Modifier
                .height(200.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.colorTransparent)),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
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

        Text(
            text = movie.title,
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.titleSmall,
            color = Color.White,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

    }
}

@Preview
@Composable
fun MovieItemPreview() {
    MovieItem(movie = movieDummyVO)
}