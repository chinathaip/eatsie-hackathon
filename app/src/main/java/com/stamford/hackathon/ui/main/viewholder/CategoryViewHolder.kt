package com.stamford.hackathon.ui.main.viewholder

import android.util.Log
import com.stamford.hackathon.core.ItemViewHolder
import com.stamford.hackathon.core.OnCategoryClick
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.databinding.ViewHolderCategoryBinding

class CategoryViewHolder(
    private val binding: ViewHolderCategoryBinding,
    onCategoryClick: OnCategoryClick
) :
    ItemViewHolder<ItemListingUiModel.Category>(binding.root) {

    private var categoryType: String? = null

    init {
        binding.categoryCardView.setOnClickListener {
            onCategoryClick.onCategoryClick(categoryType)
            Log.d("LOL", categoryType.toString())
        }
    }

    override fun fillData(data: ItemListingUiModel.Category, position: Int) {
        categoryType = data.enum
        binding.categoryNameTextView.text = data.name
    }
}