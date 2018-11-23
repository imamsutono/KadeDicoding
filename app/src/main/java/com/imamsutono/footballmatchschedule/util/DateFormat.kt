package com.imamsutono.footballmatchschedule.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun getDate(date: Date?): String? = with(date ?: Date()) {
    SimpleDateFormat("EEE, dd MMM yyy").format(this)
}

@SuppressLint("SimpleDateFormat")
fun toGMTFormat(date: String?, time: String?): Date? {
    val formatter = SimpleDateFormat("yyy/MM/dd HH:mm:ss")
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    val dateTime = "$date $time"

    return formatter.parse(dateTime)
}
