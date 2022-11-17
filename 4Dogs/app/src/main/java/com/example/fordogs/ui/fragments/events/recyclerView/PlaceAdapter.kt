package com.example.fordogs.ui.fragments.events.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.fordogs.R
import com.example.fordogs.data.remote.dto.PerroTipsRecyclerView
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.ALEGRIA_1
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.ALEGRIA_2
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.ALEGRIA_3
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.ASEO_1
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.ASEO_2
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.ASEO_3
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.ENERGIA_1
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.ENERGIA_2
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.ENERGIA_3
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.ENTRENABILIDAD_1
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.ENTRENABILIDAD_2
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.ENTRENABILIDAD_3
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.NINOS_1
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.NINOS_2
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.NINOS_3
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.PERROS_1
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.PERROS_2
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.PERROS_3
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.PROTECTOR_1
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.PROTECTOR_2
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.PROTECTOR_3
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.TITULO_ALEGRIA
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.TITULO_ASEO
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.TITULO_ENERGIA
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.TITULO_ENTRENABILIDAD
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.TITULO_NINOS
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.TITULO_PERROS
import com.example.fordogs.ui.fragments.events.recyclerView.RecyclerTipsConstants.Companion.TITULO_PROTECTOR

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
                    atribute.text = TITULO_NINOS
                    when (place.data) {
                        in 0..1 -> {
                            text.text = NINOS_1
                        }
                        in 2..3 -> {
                            text.text = NINOS_2
                        }
                        else -> {
                            text.text = NINOS_3
                        }
                    }
                }
                1 -> {
                    atribute.text = TITULO_PERROS
                    when (place.data) {
                        in 0..1 -> {
                            text.text = PERROS_1
                        }
                        in 2..3 -> {
                            text.text = PERROS_2

                        }
                        else -> {
                            text.text = PERROS_3

                        }
                    }

                }
                2 -> {
                    atribute.text = TITULO_ALEGRIA
                    when (place.data) {
                        in 0..1 -> {
                            text.text = ALEGRIA_1
                        }
                        in 2..3 -> {
                            text.text = ALEGRIA_2

                        }
                        else -> {
                            text.text = ALEGRIA_3

                        }
                    }

                }
                3 -> {
                    atribute.text = TITULO_PROTECTOR
                    when (place.data) {
                        in 0..1 -> {
                            text.text = PROTECTOR_1
                        }
                        in 2..3 -> {
                            text.text = PROTECTOR_2

                        }
                        else -> {
                            text.text = PROTECTOR_3

                        }
                    }

                }
                4 -> {
                    atribute.text = TITULO_ENTRENABILIDAD
                    when (place.data) {
                        in 0..1 -> {
                            text.text = ENTRENABILIDAD_1
                        }
                        in 2..3 -> {
                            text.text = ENTRENABILIDAD_2

                        }
                        else -> {
                            text.text = ENTRENABILIDAD_3

                        }
                    }

                }
                5 -> {
                    atribute.text = TITULO_ENERGIA
                    when (place.data) {
                        in 0..1 -> {
                            text.text = ENERGIA_1
                        }
                        in 2..3 -> {
                            text.text = ENERGIA_2

                        }
                        else -> {
                            text.text = ENERGIA_3

                        }
                    }

                }
                6 -> {
                    atribute.text = TITULO_ASEO
                    when (place.data) {
                        in 0..1 -> {
                            text.text = ASEO_1
                        }
                        in 2..3 -> {
                            text.text = ASEO_2

                        }
                        else -> {
                            text.text = ASEO_3

                        }
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