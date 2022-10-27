package com.example.fordogs.ui.fragments.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.fordogs.ui.util.CalendarConstants
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarViewModel: ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    fun daysInMonthArray(date: LocalDate?): ArrayList<LocalDate?> {

        val daysInMonthArray = arrayListOf<LocalDate?>()
        val yearMonth = YearMonth.from(date)
        val daysInMonth = yearMonth.lengthOfMonth()

        val firstOfMonth = CalendarConstants.selectedDate?.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth?.dayOfWeek?.value

        for (i in 1..42) {

            if (i <= dayOfWeek!! || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add(null)
            } else {
                daysInMonthArray.add(CalendarConstants.selectedDate?.let {
                    LocalDate.of(
                        it.year,
                        CalendarConstants.selectedDate?.month,
                        i - dayOfWeek
                    )
                })
            }

        }

        return daysInMonthArray
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun monthYearFromDate(date: LocalDate?): String {

        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")

        return date!!.format(formatter)
    }


}