package com.e.data.repository

import java.text.SimpleDateFormat
import java.util.*


class DateRepository {

    companion object {
        const val restDaysToCurrentDay = 15
        const val filmTypes = "1|2"
        const val formatPatternDate = "yyyy-MM-dd"
    }

    fun releaseFilmDate(): String{
        return getCurrentDate().format(Date())
    }

    fun finalReleaseFilmDate(): String{
        return getCurrentDate().format(getDaysAgo(restDaysToCurrentDay))
    }

    fun releaseType(): String{
        return filmTypes
    }

    private fun getCurrentDate(): SimpleDateFormat {
        return SimpleDateFormat(formatPatternDate, Locale.getDefault())
    }

    private fun getDaysAgo(daysAgo: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)

        return calendar.time
    }
}