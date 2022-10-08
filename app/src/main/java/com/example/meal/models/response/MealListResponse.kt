package com.example.meal.models.response

import com.example.meal.models.Meal

data class MealListResponse(
    val meals: List<Meal>,
)