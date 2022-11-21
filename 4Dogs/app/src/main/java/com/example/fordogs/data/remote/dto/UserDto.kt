package com.example.fordogs.data.remote.dto

import com.example.fordogs.data.local.entity.UserPerro

data class UserDto (
    val id: String = "",
    val nombre: String = "",
    val raza: String = "",
    val peso: Int   = 0,
    val color: String = "",
    val alto: Int = 0,
    val largo: Int = 0,
    val comidaFav: String = "",
    val imagen: String = ""


)
fun UserDto.mapToEntity(): UserPerro = UserPerro(
    id = id,
    nombre = nombre,
    raza = raza,
    peso = peso,
    color = color,
    alto = alto,
    largo = largo,
    comidaFav = comidaFav,
    imagen = imagen
)