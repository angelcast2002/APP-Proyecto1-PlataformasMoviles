package com.example.fordogs.ui.fragments.addevents

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.fordogs.R
import com.example.fordogs.data.local.entity.Event
import com.example.fordogs.databinding.FragmentAddEventBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*


class AddEventBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var eventsVM: EventsManagementViewModel
    private lateinit var binding: FragmentAddEventBinding
    private lateinit var alarmManager: AlarmManager
    private lateinit var event: Event
    var eventId: Int = 0
    var isEditing: Boolean = false
    private lateinit var timePickerDialog: TimePickerDialog
    private lateinit var datePickerDialog: DatePickerDialog
    var mYear = 0
    var mMonth = 0
    var mDay = 0
    var mHour = 0
    var mMinute = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        eventsVM = ViewModelProvider(activity)[EventsManagementViewModel::class.java]
        //setObservables()
        setListeners()

    }

    private fun validateFields(): Boolean {
        return if (binding.eventTitleEditText.text.toString().equals("", ignoreCase = true)) {
            Toast.makeText(activity, "Please enter a valid title", Toast.LENGTH_SHORT).show()
            false
        } else if (binding.eventDescriptionEditText.text.toString().equals("", ignoreCase = true)) {
            Toast.makeText(activity, "Please enter a valid description", Toast.LENGTH_SHORT).show()
            false
        } else if (binding.eventDateEditText.text.toString().equals("", ignoreCase = true)) {
            Toast.makeText(activity, "Please enter date", Toast.LENGTH_SHORT).show()
            false
        } else if (binding.eventHourEditText.text.toString().equals("", ignoreCase = true)) {
            Toast.makeText(activity, "Please enter time", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setListeners() {
        binding.eventDateEditText.setOnTouchListener(OnTouchListener { view: View?, motionEvent: MotionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                val c = Calendar.getInstance()
                mYear = c[Calendar.YEAR]
                mMonth = c[Calendar.MONTH]
                mDay = c[Calendar.DAY_OF_MONTH]
                datePickerDialog = DatePickerDialog(
                    requireActivity(),
                    { view1: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                        binding.eventDateEditText.setText(getString(R.string.date_format_forEvent, dayOfMonth.toString(), monthOfYear + 1, year))
                        datePickerDialog.dismiss()
                    }, mYear, mMonth, mDay
                )
                datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
                datePickerDialog.show()
            }
            true
        })

        binding.eventHourEditText.setOnTouchListener(OnTouchListener { view: View?, motionEvent: MotionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                // Get Current Time
                val c = Calendar.getInstance()
                mHour = c[Calendar.HOUR_OF_DAY]
                mMinute = c[Calendar.MINUTE]

                // Launch Time Picker Dialog
                timePickerDialog = TimePickerDialog(
                    activity,
                    { _: TimePicker?, hourOfDay: Int, minute: Int ->
                        binding.eventHourEditText.setText(getString(R.string.time_format_forEvent, hourOfDay, minute))
                        timePickerDialog.dismiss()
                    }, mHour, mMinute, false
                )
                timePickerDialog.show()
            }
            true
        })

    }

}