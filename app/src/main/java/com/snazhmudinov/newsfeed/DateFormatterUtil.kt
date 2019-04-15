package com.snazhmudinov.newsfeed

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateFormatterUtil {

    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    fun getFormattedDate(publishedAt: String): String {
        val publishedTimeStamp = dateFormatter.parse(publishedAt).time
        val currentTimeStamp = Date().time
        val diff = currentTimeStamp - publishedTimeStamp

        return when {
            TimeUnit.MILLISECONDS.toDays(diff) >= 1 -> "${TimeUnit.MILLISECONDS.toDays(diff)} day(s) ago"
            TimeUnit.MILLISECONDS.toHours(diff) <= 24 -> "${TimeUnit.MILLISECONDS.toHours(diff)} hour(s) ago"
            TimeUnit.MILLISECONDS.toMinutes(diff) <= 60 -> "${TimeUnit.MILLISECONDS.toMinutes(diff)} min(s) ago"
            else -> "N/A"

        }
    }
}