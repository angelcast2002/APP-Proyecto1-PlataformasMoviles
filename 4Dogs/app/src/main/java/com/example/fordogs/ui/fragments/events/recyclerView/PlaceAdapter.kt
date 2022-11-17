package com.example.fordogs.ui.fragments.events.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.fordogs.R
import com.example.fordogs.data.remote.dto.PerroTipsRecyclerView

class PlaceAdapter(
    private val dataSet: MutableList<PerroTipsRecyclerView>
):
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>(){

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val atribute: TextView = view.findViewById(R.id.textView_attribute_recycleView)
        private val text: TextView = view.findViewById(R.id.textView_text_recycleView)
        private val layout: ConstraintLayout = view.findViewById(R.id.layout_itemPlace)
        private lateinit var place: PerroTipsRecyclerView

        fun setData(place: PerroTipsRecyclerView) {
            this.place = place
            when(place.index) {
                0 -> {
                    atribute.text = "Bueno con los niños"
                    if (place.data in 0..1) {
                        text.text = "No acerque su perro a los niños"
                    } else if (place.data in 2..3) {
                        text.text = "Tenga precuación cuando los niños se acerquen a su perro"
                    }
                    else {
                        text.text = "A su perro le gusta estar con niños!"
                    }
                }
                1 -> {
                    atribute.text = "Bueno con otros perros"
                    if (place.data in 0..1) {
                        text.text = "No acerque su perro a los niños"
                    } else if (place.data in 2..3) {

                    }
                    else {

                    }

                }
                2 -> {
                    atribute.text = "Alegría"
                    if (place.data in 0..1) {
                        text.text = "No acerque su perro a los niños"
                    } else if (place.data in 2..3) {

                    }
                    else {

                    }

                }
                3 -> {
                    atribute.text = "Protector"
                    if (place.data in 0..1) {
                        text.text = "No acerque su perro a los niños"
                    } else if (place.data in 2..3) {

                    }
                    else {

                    }

                }
                4 -> {
                    atribute.text = "Entrenabilidad"
                    if (place.data in 0..1) {
                        text.text = "No acerque su perro a los niños"
                    } else if (place.data in 2..3) {

                    }
                    else {

                    }

                }
                5 -> {
                    atribute.text = "Energia"
                    if (place.data in 0..1) {
                        text.text = "No acerque su perro a los niños"
                    } else if (place.data in 2..3) {

                    }
                    else {

                    }

                }
                6 -> {
                    atribute.text = "Necesidad de aseo"
                    if (place.data in 0..1) {
                        text.text = "No acerque su perro a los niños"
                    } else if (place.data in 2..3) {

                    }
                    else {

                    }

                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_object, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}