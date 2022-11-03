package com.example.fordogs.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PerroTips (
    @PrimaryKey val name: String,
    val barking: Int,
    val coat_length: Int,
    val drooling: Int,
    val energy: Int,
    val good_with_children: Int,
    val good_with_other_dogs: Int,
    val good_with_strangers: Int,
    val grooming: Int,
    val image_link: String,
    val max_height_female: Double,
    val max_height_male: Double,
    val max_life_expectancy: Double,
    val max_weight_female: Double,
    val max_weight_male: Double,
    val min_height_female: Double,
    val min_height_male: Double,
    val min_life_expectancy: Double,
    val min_weight_female: Double,
    val min_weight_male: Double,
    val playfulness: Int,
    val protectiveness: Int,
    val shedding: Int,
    val trainability: Int
)