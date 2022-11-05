package com.stamford.hackathon.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stamford.hackathon.databinding.ViewHolderCategoryBinding
import com.stamford.hackathon.ui.main.viewholder.CategoryViewHolder

class CategoryAdapter : RecyclerView.Adapter<CategoryViewHolder>() {

    val categories = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ViewHolderCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = categories.size
}