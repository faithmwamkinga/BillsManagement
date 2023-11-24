package com.example.billsmanagement.utils

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters.firstDayOfMonth
import java.time.temporal.TemporalAdjusters.lastDayOfMonth
import java.time.temporal.TemporalAdjusters.nextOrSame
import java.time.temporal.TemporalAdjusters.previousOrSame

class DateTimeUtils {
    companion object {
        fun formatDate(localDateTime: LocalDateTime): String {
            val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            return localDateTime.format(format)
        }

        fun getFirstDayOfMonth(): String {
            val now = LocalDateTime.now()
            val firstDay = now.with(firstDayOfMonth())
            return formatDate(firstDay)
        }

        fun getLastDayOfMonth(): String {
            val now = LocalDateTime.now()
            val lastDay = now.with(lastDayOfMonth())
            return formatDate(lastDay)
        }

        fun getCurrentMonth(): String {
            val now = LocalDateTime.now()
            return now.month.value.toString()
        }

        fun getCurrentYear(): String {
            val now = LocalDateTime.now()
            return now.year.toString()
        }

        fun getFirstDateOfWeek(): String {
            val now = LocalDateTime.now()
            val firstDay = now.with(previousOrSame(DayOfWeek.MONDAY))
            return formatDate(firstDay)
        }

        fun getLastDateOfWeek(): String {
            val now = LocalDateTime.now()
            val lastDay = now.with(previousOrSame(DayOfWeek.SUNDAY))
            return formatDate(lastDay)
        }

        fun getDateOfWeekDay(dueDate: String): String {
            val now = LocalDateTime.now()
            val weekDay = now.with(nextOrSame(DayOfWeek.of(dueDate.toInt())))
            return formatDate(weekDay)
        }

        fun createDateFromDay(dayOfMonth: String): String {
            val now = LocalDateTime.now()
            val date = now.withDayOfMonth(dayOfMonth.toInt())
            return formatDate(date)
        }

        fun createDateFromDayAndMonth(day: Int, month: Int): String {
            val now = LocalDateTime.now()
            val date = now.withMonth(month).withDayOfMonth(day)
            return formatDate(date)
        }

        fun formatDateReadable(date: String): String {
            val originalFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val dateToFormat = LocalDate.parse(date, originalFormat)
            val readableFormat = DateTimeFormatter.ofPattern("dd MMMM, yyyy")
            return readableFormat.format(dateToFormat)
        }
        fun getDateToday():String{
            return formatDate(LocalDateTime.now())
        }
    }
}
//zero means false one means true