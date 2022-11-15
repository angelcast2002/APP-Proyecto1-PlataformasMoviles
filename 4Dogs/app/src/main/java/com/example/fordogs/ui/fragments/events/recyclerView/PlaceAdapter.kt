package com.example.fordogs.ui.fragments.events.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.example.fordogs.data.local.entity.PerroTips

class PlaceAdapter(
    private val dataSet: PerroTips,
    private val placeListener: PlaceListener
):
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    interface PlaceListener {
        fun onPlaceClicked(data: Character, position: Int)
    }

    class ViewHolder(private val view: View,
                     private val listener: PlaceListener) : RecyclerView.ViewHolder(view) {
        private val imageType: ImageView = view.findViewById(R.id.imageView_recycleViewPlace)
        private val textName: TextView = view.findViewById(R.id.textView_recycleViewPlace_name)
        private val textRaceStatus: TextView = view.findViewById(R.id.textView_recycleViewPlace_raceStatus)
        private val layout: ConstraintLayout = view.findViewById(R.id.layout_itemPlace)
        private lateinit var place: Character

        fun setData(place: Character) {
            this.place = place
            textName.text = place.name
            //textRaceStatus.text = place.race + " - " + place.status
            (place.species + " - " + place.status).also { textRaceStatus.text = it }

            imageType.load(place.image) {
                transformations(CircleCropTransformation())
                diskCachePolicy(CachePolicy.DISABLED)
                memoryCachePolicy(CachePolicy.DISABLED)
                error(R.drawable.ic_error)
                placeholder(R.drawable.ic_download)
            }
            setListeners()
        }

        private fun setListeners() {
            layout.setOnClickListener {
                listener.onPlaceClicked(place, this.adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_object, parent, false)

        return ViewHolder(view, placeListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}