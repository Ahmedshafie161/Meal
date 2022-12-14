package com.example.meal.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meal.models.Category
import com.example.meal.databinding.CategoryCardBinding

class CategoryRecyclerViewAdapter : RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryViewHolder>() {
    private var categoryList:List<Category> = ArrayList()
    private lateinit var onItemClick: OnItemCategoryClicked

    fun setCategoryList(categoryList: List<Category>){
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    fun onItemClicked(onItemClick: OnItemCategoryClicked){
        this.onItemClick = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.apply {
            tvCategoryName.text = categoryList[position].strCategory
            Glide.with(holder.itemView).load(categoryList[position].strCategoryThumb).into(imgCategory)
        }

        holder.itemView.setOnClickListener {
            onItemClick.onClickListener(categoryList[position])
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    interface OnItemCategoryClicked{
        fun onClickListener(category: Category)
    }

    class CategoryViewHolder(val binding:CategoryCardBinding):RecyclerView.ViewHolder(binding.root)
}