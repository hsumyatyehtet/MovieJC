package com.hmyh.moviejc.movieui.widget

import android.annotation.SuppressLint
import android.os.Build
import android.widget.RatingBar
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.hmyh.moviejc.R
import com.hmyh.moviejc.domain.utils.movieDetailVO
import com.hmyh.moviejc.network.extension.PHOTO_PATH
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailToolbar(posterPath: String, navController: NavController) {

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
                            Color(0xFF0e1f30),
                            Color(0xCC0e1f30),
                            Color(0x990e1f30),
                            Color(0x660e1f30)            
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
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
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
fun ToolBarPreview() {
    MovieDetailToolbar(
        posterPath = movieDetailVO.posterPath,
        navController = rememberNavController()
    )
}

@Composable
fun CardWhiteBackground() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white))
    ) {

    }
}

@SuppressLint("DefaultLocale")
@Composable
fun BoxScope.RatingCard(voteAverage: String) {

    val formattedRating = String.format("%.1f", voteAverage.toDouble())

    Card(
        modifier = Modifier
            .padding(end = 20.dp)
            .offset(y = (-30).dp)
            .width(60.dp)
            .height(60.dp)
            .align(Alignment.TopEnd),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.rating_bg)),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Rating",
                fontSize = 12.sp,
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.Bold
            )

            Text(
                text = formattedRating,
                fontSize = 14.sp,
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.SemiBold
            )
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormattedDateText(date: String): String {
    val parsedDate = LocalDate.parse(date) // parses "2025-07-25"
    val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH)
    val formattedDate = parsedDate.format(formatter) // "July 25, 2025"
    return formattedDate
}

fun FormattedTime(runtime: Int?): String{
    if (runtime == null || runtime <= 0) return "-"
    val hours = runtime / 60
    val minutes = runtime % 60
    return "${hours}h ${minutes}m"
}

@Preview(showBackground = true)
@Composable
fun RatingCardPreview() {
    Box(modifier = Modifier.fillMaxWidth()) {
        RatingCard(voteAverage = "8.5")
    }
}

@Preview
@Composable
fun PosterItem(posterPath: String?=null) {

    val fullPosterPath = PHOTO_PATH + posterPath

    Card(
        modifier = Modifier
            .width(120.dp)
            .height(180.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.background_color)),
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
            contentDescription = "Movie Detail Poster",
            contentScale = ContentScale.Crop
        )

    }
}

@Composable
fun RowScope.RoundedButton(
    modifier: Modifier,
    backgroundColor: Int,
    imageVector: ImageVector,
    imageColor: Color,
    title: String,
    onActionButton: () -> Unit
) {
    Card(
        modifier = modifier
            .weight(1f)
            .height(45.dp),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = backgroundColor)),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        onClick = {
            onActionButton.invoke()
        }
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = imageVector,
                contentDescription = "Play",
                tint = imageColor
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = imageColor
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun RoundedButtonPreview(){
    Row (modifier = Modifier.fillMaxWidth()) {
        RoundedButton(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = R.color.colorPlayButtonBackground,
            imageVector = Icons.Default.PlayArrow,
            imageColor = Color.White,
            title = "Play",
            onActionButton = {

            }
        )
    }
}
