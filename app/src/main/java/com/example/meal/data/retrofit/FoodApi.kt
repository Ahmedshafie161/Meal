package com.example.meal.data.retrofit

import com.example.meal.models.response.CategoryResponse
import com.example.meal.models.response.MealRandomResponse
import com.example.meal.models.response.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface FoodApi {
    @GET("categories.php")
    fun getCategories(): Call<CategoryResponse>

    @GET("filter.php?")
    fun getMealsByCategory(@Query("i") category: String): Call<MealResponse>

    @GET("random.php")
    fun getRandomMeal(): Call<MealRandomResponse>

    @GET("lookup.php?")
    fun getMealById(@Query("i") id: String): Call<MealRandomResponse>

    @GET("search.php?")
    fun searchMeals(@Query("s") s: String): Call<MealResponse>
}