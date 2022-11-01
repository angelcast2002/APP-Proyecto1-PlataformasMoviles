package com.example.fordogs.ui.fragments.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.*

class CalendarConstants {

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        var selectedDate: LocalDate? = LocalDate.now()
    }

}