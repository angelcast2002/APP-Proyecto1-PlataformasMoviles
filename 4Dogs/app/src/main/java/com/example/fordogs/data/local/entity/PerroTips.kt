package com.example.fordogs.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PerroTips (
    @PrimaryKey val name: String,
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
    val min_weight_female: Double
)