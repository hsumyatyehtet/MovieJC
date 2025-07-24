package com.hmyh.moviejc.movieui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieTitle(title: String,onClickSeeAll: ()-> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp,),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(vertical = 4.dp),
            text = title,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )

        Text(
            modifier = Modifier
                .clickable{
                    onClickSeeAll()
                },
            text = "See All",
            color = Color.White,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
        )
    }

}

@Preview
@Composable
fun MovieTitlePreview() {
    MovieTitle(title = "Now Playing", onClickSeeAll = {

    })
}