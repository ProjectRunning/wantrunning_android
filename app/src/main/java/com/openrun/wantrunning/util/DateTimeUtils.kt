package com.openrun.wantrunning.util

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.math.min

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

    fun getDayOfWeekForKR(dayOfWeek: DayOfWeek): String {
        return when (dayOfWeek) {
            DayOfWeek.MONDAY -> "월요일"
            DayOfWeek.TUESDAY -> "화요일"
            DayOfWeek.WEDNESDAY -> "수요일"
            DayOfWeek.THURSDAY -> "목요일"
            DayOfWeek.FRIDAY -> "금요일"
            DayOfWeek.SATURDAY -> "토요일"
            DayOfWeek.SUNDAY -> "일요일"
        }
    }

    fun getMidday(dateTime: LocalDateTime): Midday {
        return if (dateTime.hour in 0..11) Midday.오전 else Midday.오후
    }

    fun getMiddayByIntValue(intVal: Int): Midday {
        return Midday.values().find { midday -> midday.intVal == intVal } ?: Midday.오전
    }

    fun getMiddayHour(dateTime: LocalDateTime): Int {
        return when (dateTime.hour) {
            in 1..12 -> dateTime.hour
            in 13..23 -> dateTime.hour - 12
            else -> 12
        }
    }

    fun getMiddayLocalTime(midday: Midday, hour: Int, minute: Int): LocalTime {
        return when {
            midday == Midday.오전 && hour == 12 -> LocalTime.of(0, minute)
            midday == Midday.오후 && hour in 1..11 -> LocalTime.of(hour + 12, minute)
            else -> LocalTime.of(hour, minute)
        }
    }

    fun isValidDateTime(year: Int, monthValue: Int, dayOfMonth: Int, midday: Midday, hour: Int, minute: Int): Boolean {
        val now = LocalDateTime.now()

        val argsDate = LocalDate.of(year, monthValue, dayOfMonth)
        val argsTime = getMiddayLocalTime(midday, hour, minute)
        val argsDateTime = LocalDateTime.of(argsDate, argsTime)

        return now.isBefore(argsDateTime)
    }
}