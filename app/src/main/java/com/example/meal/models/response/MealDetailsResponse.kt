package com.example.meal.models.response

import com.example.meal.models.MealDetails

data class MealDetailsResponse(
    val mealDetailsList: List<MealDetails>,
)