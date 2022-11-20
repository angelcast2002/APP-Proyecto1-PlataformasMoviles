package com.example.fordogs.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserPerro (

    @PrimaryKey var id: String = "0",
    var nombre: String = "Nombre",
    var raza: String = "Raza",
    var peso: Int = 0,
    var color: String = "Color",
    var alto: Int = 0,
    var largo: Int = 0,
    var comidaFav: String = "Comida",
    var imagen: String = "https://www.petdarling.com/wp-content/uploads/2020/11/razas-de-perros.jpg"
)
