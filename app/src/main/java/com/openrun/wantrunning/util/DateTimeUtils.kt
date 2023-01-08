package com.openrun.wantrunning.util

object DateTimePickerUtils {

    val years: List<Int> = (1970..2100).toList()
    val displayedValuesForYears: List<String> = years.map { it.toString() }
    val months: List<Int> = (1..12).toList()
    val displayedValuesForMonths: List<String> = months.map { it.toString() }

    enum class Midday(val intVal: Int) {
        오전(0), 오후(1)
    }

    val midday: List<Int> = Midday.values().map { it.intVal }
    val displayedValuesForMidday: List<String> = Midday.values().map { it.toString() }
    val hours: List<Int> = (1..12).toList()
    val displayedValuesForHours: List<String> = hours.map { it.toString() }
    val minutes: List<Int> = (0..59).toList()
    val displayedValuesForMinutes: List<String> = minutes.map { it.toString() }

    fun getDayOfMonthList(year: Int, month: Int): List<Int> {
        val leapYear = year % 4 == 0 && year % 100 != 0 || year % 400 == 0
        return when (month) {
            1, 3, 5, 7, 8, 10, 12 -> (1..31)
            4, 6, 9, 11 -> (1..30)
            2 -> if (leapYear) (1..29) else (1..28)
            else -> throw IllegalArgumentException("invalid month value")
        }.toList()
    }
}