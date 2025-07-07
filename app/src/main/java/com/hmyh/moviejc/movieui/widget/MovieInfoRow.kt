package com.hmyh.moviejc.movieui.widget

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 4.dp)
    ) {
        Text(
            text = label,
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
        )

        Text(
            text = value,
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InfoRowDate(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 4.dp)
    ) {
        Text(
            text = label,
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = formatDateString(value),
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun InfoRowHour(label: String, value: Int) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 4.dp)
    ) {
        Text(
            text = label,
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
        )

        Text(
            text = formatMinutesToHourMinute(value.toInt()),
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateString(rawDate: String): String {
    return try {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)
        val outputFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.US)
        val localDate = LocalDate.parse(rawDate, inputFormatter)
        outputFormatter.format(localDate)
    } catch (e: Exception) {
        "Invalid Date"
    }
}

@SuppressLint("DefaultLocale")
fun formatMinutesToHourMinute(totalMinutes: Int): String {
    val hours = totalMinutes / 60
    val minutes = totalMinutes % 60
    return String.format("%d:%02d", hours, minutes)
}
