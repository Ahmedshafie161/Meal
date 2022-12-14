package com.example.meal.presentation.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.meal.*
import com.example.meal.presentation.ui.activities.DetailsActivity
import com.example.meal.utils.*
//import com.example.easyfood.ui.fragments.HomeFragment
//import com.example.easyfood.util.Constants.Companion.CATEGORY_NAME
//import com.example.easyfood.util.Constants.Companion.MEAL_AREA
//import com.example.easyfood.util.Constants.Companion.MEAL_ID
//import com.example.easyfood.util.Constants.Companion.MEAL_NAME
//import com.example.easyfood.util.Constants.Companion.MEAL_STR
//import com.example.easyfood.util.Constants.Companion.MEAL_THUMB
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MealBottomDialogFragment : BottomSheetDialogFragment() {
    private var mealName = ""
    private var mealId = ""
    private var mealImg = ""
    private var mealCountry = ""
    private var mealCategory = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
        val b = arguments
        mealName = b!!.getString(MEAL_NAME).toString()
        b.getString(MEAL_ID).toString().also { mealId = it }
        mealImg = b.getString(MEAL_THUMB).toString()
        mealCategory = b.getString(CATEGORY_NAME).toString()
        mealCountry = b.getString(MEAL_AREA).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.bottom_sheet_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareView(view)

        view.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(MEAL_ID, mealId)
            intent.putExtra(MEAL_STR, mealName)
            intent.putExtra(MEAL_THUMB, mealImg)
            startActivity(intent)
        }
    }

    private fun prepareView(view: View) {
        val tvMealName = view.findViewById<TextView>(R.id.tv_meal_name_in_btmsheet)
        val tvMealCategory = view.findViewById<TextView>(R.id.tv_meal_category)
        val tvMealCountry = view.findViewById<TextView>(R.id.tv_meal_country)
        val imgMeal = view.findViewById<ImageView>(R.id.img_category)

        Glide.with(view).load(mealImg).into(imgMeal)
        tvMealName.text = mealName
        tvMealCategory.text = mealCategory
        tvMealCountry.text = mealCountry
    }
}