package com.hmyh.moviejc.domain.feature.moviedetail.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.hmyh.moviejc.appbase.core.ObjViewState
import com.hmyh.moviejc.network.extension.PHOTO_PATH

data class MovieDetail(
    val id: Long,
    val backDropPack: String,
    val originalTitle: String,
    val homePage: String,
    val originalCountry: List<String>,
    val overView: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: String,
    val genreList: List<GenreEntity?>,
    val runtime: Int
) {

    data class GenreEntity(
        val id: Long,
        val name: String
    )
}
