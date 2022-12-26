package com.openrun.wantrunning.util

object DateTimeUtils {

    fun getMonthlyDays(year: Int, month: Int): List<Int> {
        val leapYear = year % 4 == 0 && year % 100 != 0 || year % 400 == 0
        return when (month) {
            1, 3, 5, 7, 8, 10, 12 -> (1..31)
            4, 6, 9, 11 -> (1..30)
            2 -> if (leapYear) (1..29) else (1..28)
            else -> throw IllegalArgumentException("invalid month value")
        }.toList()
    }
}