package com.example.meal.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.meal.*
import com.example.meal.models.Meal
import com.example.meal.databinding.ActivityCategoriesBinding
import com.example.meal.presentation.ui.adapters.MealRecyclerViewAdapter
import com.example.meal.presentation.ui.adapters.MealRecyclerViewAdapter.SetOnMealClickListener

import com.example.meal.utils.CATEGORY_NAME
import com.example.meal.utils.MEAL_ID
import com.example.meal.utils.MEAL_STR
import com.example.meal.utils.MEAL_THUMB
import com.example.meal.presentation.viewmodels.MealActivityViewModel


class MealActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoriesBinding
    private lateinit var mealActivityViewModel: MealActivityViewModel
    private lateinit var mealAdapter: MealRecyclerViewAdapter
    private var categoryName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealActivityViewModel = ViewModelProviders.of(this)[MealActivityViewModel::class.java]

        startLoading()
        prepareRecyclerView()
        mealActivityViewModel.getMealsByCategory(getCategory())

        mealActivityViewModel.observeMeal().observe(this) { t ->
            if (t == null) {
                hideLoading()
                Toast.makeText(applicationContext, "No meals in this category", Toast.LENGTH_SHORT).show()
                onBackPressed()
            } else {
                mealAdapter.setCategoryList(t)
                binding.tvCategoryCount.text = categoryName + " : " + t.size.toString()
                hideLoading()
            }
        }

        mealAdapter.setOnMealClickListener(object : SetOnMealClickListener {
            override fun setOnClickListener(meal: Meal) {
                val intent = Intent(applicationContext, DetailsActivity::class.java)
                intent.putExtra(MEAL_ID, meal.idMeal)
                intent.putExtra(MEAL_STR, meal.strMeal)
                intent.putExtra(MEAL_THUMB, meal.strMealThumb)
                startActivity(intent)
            }
        })
    }

    private fun hideLoading() {
        binding.apply {
            loadingGifMeals.visibility = View.INVISIBLE
            mealRoot.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.white))
        }
    }

    private fun startLoading() {
        binding.apply {
            loadingGifMeals.visibility = View.VISIBLE
            mealRoot.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.g_loading))
        }
    }

    private fun getCategory(): String {
        val x = intent.getStringExtra(CATEGORY_NAME)!!
        categoryName = x
        return x
    }

    private fun prepareRecyclerView() {
        mealAdapter = MealRecyclerViewAdapter()
        binding.mealRecyclerview.apply {
            adapter = mealAdapter
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        }
    }
}