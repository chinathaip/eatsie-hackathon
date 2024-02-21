package com.stamford.hackathon.ui.main.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stamford.hackathon.core.ItemViewHolder
import com.stamford.hackathon.core.OnCategoryClick
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.databinding.ViewHolderCategoryContainerBinding
import com.stamford.hackathon.ui.main.adapter.CategoryAdapter

class CategoryContainerViewHolder(
    binding: ViewHolderCategoryContainerBinding,
    onCategoryClickListener: OnCategoryClick
) :
    ItemViewHolder<ItemListingUiModel.ListOfCategory>(binding.root) {

    private val categoryAdapter = CategoryAdapter(onCategoryClickListener)

    init {
        binding.categoriesRecyclerView.layoutManager =
            LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
        binding.categoriesRecyclerView.adapter = categoryAdapter
    }

    override fun fillData(data: ItemListingUiModel.ListOfCategory, position: Int) {
        categoryAdapter.apply {
            categories.clear()
            categories.addAll(data.categories)
            notifyItemRangeChanged(0, data.categories.size)
        }
    }
}