package com.hmyh.moviejc.movieui.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.hmyh.moviejc.R
import com.hmyh.moviejc.domain.feature.home.model.Movie
import com.hmyh.moviejc.movieui.navagation.MovieScreens
import com.hmyh.moviejc.network.extension.PHOTO_PATH

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMovie(navController: NavController, movieList: List<Movie>) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,

                    ),
                title = {
                    Text(
                        text = "Playing Now",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            MainContent(navController, movieList)

        }
    }

}

@Composable
fun TwoColumnGrid(movieList: List<Movie>,onItemClick: (Long) -> Unit ={}) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(movieList) { movie ->
            MoveItem(movie, onItemClick = {
                onItemClick(it)
            })
        }
    }

}

@Composable
fun MoveItem(movie: Movie, onItemClick: (Long) -> Unit = {}) {

    val fullPosterPath = PHOTO_PATH + movie.posterPath

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp)
            .clickable(onClick = {
                onItemClick(movie.id)
            })
    ) {
        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .size(200.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        ) {

            Image(
                painter = rememberAsyncImagePainter(model = fullPosterPath),
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

        }

        Text(
            text = movie.title,
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.titleSmall,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewMoveItem() {
    val sampleMovie = Movie(
        id = 1,
        originalTitle = "Sample Movie",
        title = "Ballerina",
        popularity = 759.5684f,
        posterPath = "/2VUmvqsHb6cEtdfscEA6fqqVzLg.jpg",
        releaseDate = "2025-06-04",
        voteCount = 10
    )
    MoveItem(movie = sampleMovie, onItemClick = {})
}


@Composable
fun MainContent(navController: NavController, movieList: List<Movie>) {
    TwoColumnGrid(movieList, onItemClick = {
        navController.navigate(route = MovieScreens.DetailMovie.name+"/$it")
    })

}
