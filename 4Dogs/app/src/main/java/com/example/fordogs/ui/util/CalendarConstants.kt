package com.example.fordogs.ui.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

class CalendarConstants {

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        var selectedDate: LocalDate = LocalDate.now()
    }

}