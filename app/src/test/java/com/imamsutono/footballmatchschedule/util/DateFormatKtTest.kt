package com.imamsutono.footballmatchschedule.util

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class DateFormatKtTest {

    private val datePattern = "2014/12/29"

    @Test
    fun changeDatePattern() {
        val dateFormatted = datePattern.replace("/", "-")
        assertEquals("2014-12-29", dateFormatted)
    }

    @Test
    fun getDate() {
        val date = Date(datePattern)
        val dateFormatted = com.imamsutono.footballmatchschedule.util.getDate(date)

        assertEquals("Mon, 29 Dec 2014", dateFormatted)
    }

    @Test
    fun toGMTFormat() {
        val time = "20:00:00+00:00"
        val timeFormatted = com.imamsutono.footballmatchschedule.util.toGMTFormat(datePattern, time)
        val hours = SimpleDateFormat("HH:mm").format(timeFormatted)

        assertEquals("03:00", hours)
    }
}