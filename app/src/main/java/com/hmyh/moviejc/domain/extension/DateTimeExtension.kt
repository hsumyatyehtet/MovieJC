package com.hmyh.moviejc.domain.extension

import android.os.Build
import androidx.annotation.RequiresApi
import com.hmyh.moviejc.domain.utils.DMY_DATE_FORMAT
import com.hmyh.moviejc.domain.utils.SERVER_DATE_FORMAT
import com.hmyh.moviejc.domain.utils.SERVER_TIME_ZONE_FORMAT
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.logging.Logger
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
fun String.dateTimeFormatter(parse: String, format: String): String {
    val formatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH)
    val parser = DateTimeFormatter.ofPattern(parse, Locale.ENGLISH)
    return formatter.format(parser.parse(this.trim()))
}

@Suppress("NewApi")
fun String.formatUserFriendlyFormat() : String{
 val currentTimeStamp = Instant.now().minusMillis(Instant.parse(this).toEpochMilli()).toEpochMilli()
    val diffDays: Long = currentTimeStamp / (60 * 60 * 1000 * 24)
    val diffWeeks: Long = currentTimeStamp / (60 * 60 * 1000 * 24 * 7)
    val diffMonths = (currentTimeStamp / (60 * 60 * 1000 * 24 * 30.41666666)).roundToInt()
    val diffYears: Long = currentTimeStamp / (60L * 60 * 1000 * 24 * 365)

    return if (diffDays < 1) {
        "gg hours"
    } else if (diffWeeks < 1) {
        if(diffDays > 1) "$diffDays days ago" else "$diffDays day ago"
    } else if (diffMonths < 1) {
        if(diffWeeks > 1) "$diffWeeks weeks ago" else "$diffWeeks week ago"
    } else if (diffYears < 1) {
        if(diffMonths.toLong() > 1) "$diffMonths months ago" else "$diffMonths month ago"
    } else {
        if(diffYears > 1) "$diffYears years ago" else "$diffYears year ago"
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun LocalDate.dateTimeFormatter(format: String): String {
    val formatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH)
    return formatter.format(this)
}

@RequiresApi(Build.VERSION_CODES.O)
private fun String.localDateParser(parse: String): LocalDate {
    val parser = DateTimeFormatter.ofPattern(parse, Locale.ENGLISH)
    return LocalDate.parse(this, parser)
}

fun String.dateParser(parserFormat: String = SERVER_DATE_FORMAT, isUTC: Boolean = false): Date {
    return try {
        val parser = SimpleDateFormat(parserFormat, Locale.ENGLISH)
        if (isUTC) parser.timeZone = TimeZone.getTimeZone("UTC")
        parser.parse(this)
    } catch (e: Exception) {
        print("exception when parsing created date")
        Date()
    }
}

fun Date.dateFormatter(format: String = SERVER_DATE_FORMAT): String {
    val formatter = SimpleDateFormat(format, Locale.ENGLISH)
    return formatter.format(this)
}

fun Date.dateFormatterDMY(format: String = DMY_DATE_FORMAT): String {
    val formatter = SimpleDateFormat(format, Locale.ENGLISH)
    return formatter.format(this)
}

fun getLastWeekDate(): Array<String> {
    val calendar = Calendar.getInstance()
    val toDate = calendar.time.dateFormatter()
    calendar.add(Calendar.DATE, -7)
    val fromDate = calendar.time.dateFormatter()
    return arrayOf(fromDate, toDate)
}

fun getLastMonthDate(): Array<String> {
    val calendar = Calendar.getInstance()
    val toDate = calendar.time.dateFormatter()
    calendar.add(Calendar.MONTH, -1)
    val fromDate = calendar.time.dateFormatter()
    return arrayOf(fromDate, toDate)
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.formatLocalDate(
    formatter: String,
    parser: String = SERVER_TIME_ZONE_FORMAT,
): String {
    return runCatching {
        this.localDateParser(parser).dateTimeFormatter(formatter)
    }.getOrElse { this }
}

fun String.formatDate(
    formatter: String,
    parser: String = SERVER_TIME_ZONE_FORMAT,
    isUTC: Boolean = false
): String {
    return runCatching {
        this.dateParser(parser, isUTC).dateFormatter(formatter)
    }.getOrElse { this }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun formatDateWithoutCatch(
    formatter: DateTimeFormatter,
    parser: DateTimeFormatter,
    stringToFormat: String
): String {
    return formatter.format(parser.parse(stringToFormat))
}

fun Date.isDateWithin3Days(): Boolean {
    val currentDate = Date()
    val difference = currentDate.time - this.time
    val daysDifference = difference / (24 * 60 * 60 * 1000)
    return daysDifference in 0..2
}

fun Date.isDateWithin7Days(): Boolean {
    val currentDate = Date()
    val difference = currentDate.time - this.time
    val daysDifference = difference / (24 * 60 * 60 * 1000)
    return daysDifference <= 7
}

fun Date.isDateWithinExpiration(): Boolean {
    return Date() <= this
}