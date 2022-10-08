package com.example.meal.data.db

import androidx.lifecycle.LiveData
import com.example.meal.models.MealDataBase

interface RoomRepository {

    val mealList: LiveData<List<MealDataBase>>

    fun insertFavoriteMeal(meal: MealDataBase)

    fun getMealById(mealId: String): MealDataBase

    fun deleteMealById(mealId: String)

    fun deleteMeal(meal: MealDataBase)
}