package com.example.fordogs.ui.fragments.addevents

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.fordogs.R
import com.example.fordogs.data.local.entity.Event
import com.example.fordogs.databinding.FragmentAddEventBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*


class AddEventBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var eventsVM: EventsManagementViewModel
    private lateinit var binding: FragmentAddEventBinding
    private lateinit var eventItem: Event
    private var isEditing: Boolean = false
    private lateinit var timePickerDialog: TimePickerDialog
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var navController: NavController
    var eventId: Int = 0
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
        navController = findNavController()
        eventsVM = ViewModelProvider(activity)[EventsManagementViewModel::class.java]
        setObservables()
        setListeners()
        setAddEditEvent(eventId)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        navController.popBackStack(R.id.calendarFragment, true)
        navController.navigate(R.id.calendarFragment)
    }

    private fun validateFields(): Boolean {
        return if (binding.eventTitleEditText.text.toString().equals("", ignoreCase = true)) {
            Toast.makeText(activity, "Por favor introduzca un título", Toast.LENGTH_SHORT).show()
            false
        } else if (binding.eventDateEditText.text.toString().equals("", ignoreCase = true)) {
            Toast.makeText(activity, "Por favor introduzca una fecha ", Toast.LENGTH_SHORT).show()
            false
        } else if (binding.eventHourEditText.text.toString().equals("", ignoreCase = true)) {
            Toast.makeText(activity, "Por favor introduzca una hora", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun setObservables() {
        lifecycleScope.launchWhenStarted {
            eventsVM.eventStatus.collect { status ->
                handleStatus(status)
            }
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
                        if (hourOfDay < 10 && minute < 10) {
                            binding.eventHourEditText.setText(getString(R.string.time_format_forEvent_Units, hourOfDay, minute))
                        } else if (hourOfDay < 10) {
                            binding.eventHourEditText.setText(getString(R.string.time_format_forEvent_HourUnit, hourOfDay, minute))
                        } else if (minute < 10) {
                            binding.eventHourEditText.setText(getString(R.string.time_format_forEvent_MinuteUnit, hourOfDay, minute))
                        } else {
                            binding.eventHourEditText.setText(getString(R.string.time_format_forEvent, hourOfDay, minute))
                        }
                        timePickerDialog.dismiss()
                    }, mHour, mMinute, false
                )
                timePickerDialog.show()
            }
            true
        })

        binding.btnCreateEvent.setOnClickListener {
            if (validateFields()) {
                saveEvent()
            }
        }
    }

    private fun handleStatus(eventStatus: EventsManagementViewModel.EventStatus) {
        when (eventStatus) {
            is EventsManagementViewModel.EventStatus.Success -> {
                val message = eventStatus.message
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                eventsVM.setDefault()
                dismiss()
            }
            is EventsManagementViewModel.EventStatus.Error -> {
                val message = eventStatus.message
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                eventsVM.setDefault()
            }
            is EventsManagementViewModel.EventStatus.Editing -> {
                val details = eventStatus.event
                binding.apply {
                    addEventTitle.text = getString(R.string.modificarEvento_title)
                    eventTitleEditText.setText(details.eventTitle)
                    eventDescriptionEditText.setText(details.eventDescription)
                    eventDateEditText.setText(details.eventDate)
                    eventHourEditText.setText(details.lastAlarm)
                }
            }
            is EventsManagementViewModel.EventStatus.Default -> {
                binding.apply{
                    addEventTitle.text = getString(R.string.nuevo_evento_title)
                    eventTitleEditText.setText("")
                    eventDescriptionEditText.setText("")
                    eventDateEditText.setText("")
                    eventHourEditText.setText("")
                }
            }
        }
    }

    private fun saveEvent() {
        eventItem = Event(
            eventTitle = binding.eventTitleEditText.text.toString(),
            eventDescription = binding.eventDescriptionEditText.text.toString(),
            eventDate = binding.eventDateEditText.text.toString(),
            lastAlarm = binding.eventHourEditText.text.toString(),
            eventId = eventId
        )
        eventsVM.saveEvent(eventItem, isEditing)
    }

    fun setEventId(eventId: Int, isEditing: Boolean) {
        this.eventId = eventId
        this.isEditing = isEditing
    }

    private fun setAddEditEvent(eventId: Int) {
        if(isEditing) {
            eventsVM.editEvent(eventId)
        } else {
            eventsVM.setDefault()
        }
    }

}