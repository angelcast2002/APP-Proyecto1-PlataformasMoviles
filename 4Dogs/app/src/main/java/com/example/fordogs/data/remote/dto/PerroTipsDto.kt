package com.example.fordogs.data.remote.dto

import com.example.fordogs.data.local.entity.PerroTips

data class PerroTipsDto(
    val image_link: String,
    val good_with_children: Int,
    val good_with_other_dogs: Int,
    val shedding: Int,
    val grooming: Int,
    val drooling: Int,
    val coat_length: Int,
    val good_with_strangers: Int,
    val playfulness: Int,
    val protectiveness: Int,
    val trainability: Int,
    val energy: Int,
    val barking: Int,
    val min_life_expectancy: Double,
    val max_life_expectancy: Double,
    val max_height_male: Double,
    val max_height_female: Double,
    val max_weight_male: Double,
    val max_weight_female: Double,
    val min_height_male: Double,
    val min_height_female: Double,
    val min_weight_male: Double,
    val min_weight_female: Double,
    val name: String

)

fun PerroTipsDto.mapToEntity(): PerroTips = PerroTips(
    name = name,
    image_link,
    good_with_children,
    good_with_other_dogs,
    shedding,
    grooming,
    drooling,
    coat_length,
    good_with_strangers,
    playfulness,
    protectiveness,
    trainability,
    energy,
    barking,
    min_life_expectancy,
    max_life_expectancy,
    max_height_male,
    max_height_female,
    max_weight_male,
    max_weight_female,
    min_height_male,
    min_height_female,
    min_weight_male,
    min_weight_female
)