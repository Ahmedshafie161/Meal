package com.example.meal.models

import androidx.room.Entity

@Entity(tableName = "favorites")
data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    var category: String? = null
)