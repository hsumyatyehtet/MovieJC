@file:OptIn(ExperimentalMaterial3Api::class)

package com.hmyh.moviejc.movieui.feature.detail

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

@Composable
fun DetailMovie(navController: NavController,id: Long) {

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

            MovieDetailContent(id)

        }
    }

}

@Preview(showBackground = true)
@Composable
fun MovieDetailContent(id: Long?=null) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp)
    ) {

        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .size(250.dp)
                    .padding(top = 4.dp, bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        ) {
            Image(
                painter = painterResource(id= R.drawable.movie),
                contentDescription = "detail",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        InfoRow(label = "Original Title:", value = "The Old Guard2")
        InfoRow(label = "Release on:", value = "July 6, 2025")
        InfoRow(label = "Lasts:", value = "1h 30m")

        Text(
            text = "Taking place during the events of John Wick: Chapter 3 – Parabellum, Eve Macarro begins her training in the assassin traditions of the Ruska Roma.",
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 4.dp)
        )

        Box(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 16.dp)
                .wrapContentSize()
                .background(
                    color = Color.DarkGray,
                    shape = RoundedCornerShape(4.dp)
                )
            ,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Hello",
                color = Color.White,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 16.dp, end = 16.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }

    }

}