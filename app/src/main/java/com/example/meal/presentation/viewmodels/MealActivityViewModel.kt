package com.example.meal.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.meal.models.Meal
import com.example.meal.models.response.MealResponse
import com.example.meal.data.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealActivityViewModel():ViewModel() {
    private var mutableMeal = MutableLiveData<List<Meal>>()

    fun getMealsByCategory(category:String){
        RetrofitInstance.foodApi.getMealsByCategory(category).enqueue(object : Callback<MealResponse>{
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                mutableMeal.value = response.body()!!.meals
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Log.d(TAG,t.message.toString())
            }
        })
    }

    fun observeMeal():LiveData<List<Meal>>{
        return mutableMeal
    }

    companion object {
        const val TAG = "MealActivityViewModel"
    }
}