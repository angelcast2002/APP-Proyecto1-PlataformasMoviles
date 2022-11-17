package com.example.fordogs.ui.fragments.calendar.eventRecyclerView

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
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
import com.example.fordogs.ui.fragments.calendar.CalendarFragment

class EventsAdapter(
    private val context: MainActivity,
    private val fragmentContext: CalendarFragment,
    private val eventsList: List<Event>,
    private val listener: EventOptionsListener
) : RecyclerView.Adapter<EventViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder
    {
        val binding = EventCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int)
    {
        holder.setData(eventsList[position])
        holder.binding.options.setOnClickListener { view: View? -> showPopupMenu(view, position) }
    }

    private fun showPopupMenu(view: View?, position: Int) {
        val event = eventsList[position]
        val popupMenu = PopupMenu(view?.context, view)
        popupMenu.menuInflater.inflate(R.menu.more_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menuDelete -> {
                    val alertDialogBuilder = AlertDialog.Builder(
                        fragmentContext.requireContext(), R.style.AppTheme_Dialog
                    )
                    alertDialogBuilder.setTitle(R.string.eliminar_confirmacion)
                        .setMessage(R.string.sureToDelete)
                        .setPositiveButton(R.string.text_Si) { _: DialogInterface?, _: Int ->
                            listener.deleteEventFromId(
                                event.eventId
                            )
                        }
                        .setNegativeButton(R.string.text_Cancelar) { dialog: DialogInterface, _: Int -> dialog.cancel() }
                        .show()
                }
                R.id.menuUpdate -> {
                    val createEventBottomSheetFragment = AddEventBottomSheetFragment()
                    //createEventBottomSheetFragment.setEventId(event.eventId, true)
                    createEventBottomSheetFragment.show(
                        context.supportFragmentManager,
                        createEventBottomSheetFragment.tag
                    )
                }
                R.id.menuComplete -> {
                    val completeAlertDialog = AlertDialog.Builder(
                        fragmentContext.requireContext(), R.style.AppTheme_Dialog
                    )
                    completeAlertDialog.setTitle(R.string.confirmation)
                        .setMessage(R.string.sureToMarkAsComplete)
                        .setPositiveButton(R.string.text_Si) { _: DialogInterface?, _: Int ->
                            showCompleteDialog(
                                event.eventId
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

    private fun showCompleteDialog(eventId: Int) {
        val dialog = Dialog(fragmentContext.requireContext())
        dialog.setContentView(R.layout.event_completed_dialog)
        val close = dialog.findViewById<Button>(R.id.closeButton)
        close.setOnClickListener {
            listener.deleteEventFromId(eventId)
            dialog.dismiss()
        }
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    override fun getItemCount(): Int = eventsList.size

}
