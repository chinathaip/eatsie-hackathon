package com.stamford.hackathon.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stamford.hackathon.core.ItemViewHolder
import com.stamford.hackathon.core.OnCategoryClick
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.databinding.ViewHolderCategoryBinding
import com.stamford.hackathon.ui.main.viewholder.CategoryViewHolder

class CategoryAdapter(private val onCategoryClick: OnCategoryClick) :
    RecyclerView.Adapter<ItemViewHolder<ItemListingUiModel.Category>>() {

    val categories = mutableListOf<ItemListingUiModel.Category>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder<ItemListingUiModel.Category> {
        return CategoryViewHolder(
            ViewHolderCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onCategoryClick
        )
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder<ItemListingUiModel.Category>,
        position: Int
    ) {
        holder.fillData(categories[position], position)
    }

    override fun getItemCount(): Int = categories.size
}