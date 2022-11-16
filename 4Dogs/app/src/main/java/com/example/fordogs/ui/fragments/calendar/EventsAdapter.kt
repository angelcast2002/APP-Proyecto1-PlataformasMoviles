package com.example.fordogs.ui.fragments.calendar

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.fordogs.R
import com.example.fordogs.data.local.entity.Event
import com.example.fordogs.databinding.EventCardBinding
import com.example.fordogs.ui.MainActivity
import com.example.fordogs.ui.fragments.addevents.AddEventBottomSheetFragment
import java.text.SimpleDateFormat
import java.util.*

class EventsAdapter(
    private val context: MainActivity,
    private val eventsList: List<Event>
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    var dateFormat = SimpleDateFormat("EE dd MMM yyyy", Locale.US)
    var inputDateFormat = SimpleDateFormat("dd-M-yyyy", Locale.US)
    var date: Date? = null
    var outputDateString: String? = ""

    inner class EventViewHolder(val binding: EventCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder
    {
        val binding = EventCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int)
    {
        val event = eventsList[position]

        holder.binding.title.text = event.eventTitle
        holder.binding.description.text = event.eventDescription
        holder.binding.time.text = event.lastAlarm
        holder.binding.status!!.text = if (event.isComplete) "COMPLETADO" else "PENDIENTE"
        holder.binding.options!!.setOnClickListener { view: View? -> showPopupMenu(view, position) }

        try {
            val copyOutputDateString: String? = outputDateString
            date = inputDateFormat.parse(event.eventDate)
            outputDateString = dateFormat.format(date)
            val items1 = copyOutputDateString?.split(" ")?.toTypedArray()
            val day = items1?.get(0)
            val dd = items1?.get(1)
            val month = items1?.get(2)
            holder.binding.day!!.text = day
            holder.binding.date!!.text = dd
            holder.binding.month!!.text = month
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showPopupMenu(view: View?, position: Int) {
        val event = eventsList[position]
        val popupMenu = PopupMenu(view?.context, view)
        popupMenu.menuInflater.inflate(R.menu.more_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menuDelete -> {
                    val alertDialogBuilder = AlertDialog.Builder(
                        context, R.style.AppTheme_Dialog
                    )
                    alertDialogBuilder.setTitle(R.string.eliminar_confirmacion)
                        .setMessage(R.string.sureToDelete)
                        .setPositiveButton(R.string.text_Si) { dialog: DialogInterface?, which: Int ->
                            deleteEventFromId(
                                event.eventId,
                                position
                            )
                        }
                        .setNegativeButton(R.string.text_Cancelar) { dialog: DialogInterface, which: Int -> dialog.cancel() }
                        .show()
                }
                R.id.menuUpdate -> {
                    val createEventBottomSheetFragment = AddEventBottomSheetFragment()
                    createEventBottomSheetFragment.setEventId(event.eventId, true, context, context)
                    createEventBottomSheetFragment.show(
                        context.supportFragmentManager,
                        createEventBottomSheetFragment.tag
                    )
                }
                R.id.menuComplete -> {
                    val completeAlertDialog = AlertDialog.Builder(
                        context, R.style.AppTheme_Dialog
                    )
                    completeAlertDialog.setTitle(R.string.confirmation)
                        .setMessage(R.string.sureToMarkAsComplete)
                        .setPositiveButton(R.string.text_Si) { dialog: DialogInterface?, which: Int ->
                            showCompleteDialog(
                                event.eventId,
                                position
                            )
                        }
                        .setNegativeButton(R.string.text_Cancelar) { dialog: DialogInterface, which: Int -> dialog.cancel() }
                        .show()
                }
            }
            false
        }
        popupMenu.show()
    }

    fun showCompleteDialog(eventId: Int, position: Int) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.event_completed_dialog)
        val close = dialog.findViewById<Button>(R.id.closeButton)
        close.setOnClickListener { view: View? ->
            dialog.dismiss()
        }
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    override fun getItemCount(): Int = eventsList.size

}
