package com.example.fordogs.ui.fragments.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.entity.userPerro
import com.example.fordogs.data.repository.UserPerroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val repository: UserPerroRepository
): ViewModel() {

    private val _status = MutableStateFlow<Status>(Status.Loading)
    val status : StateFlow<Status> = _status

    sealed class Status{
        object Loading: Status()
        class Succes(val data: userPerro): Status()
        class Error(val message: String): Status()
    }

    fun getData(){
        viewModelScope.launch {
            _status.value = Status.Loading
            when(val perroInfoResult = repository.getUserPerroInfo()){
                is Resource.Succes -> {
                    _status.value = Status.Succes(perroInfoResult.data!!)
                }
                is Resource.Error -> {
                    _status.value = Status.Error(perroInfoResult.message!!)
                }
            }
        }
    }

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