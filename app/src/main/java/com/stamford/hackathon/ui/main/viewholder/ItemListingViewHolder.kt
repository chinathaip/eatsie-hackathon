package com.stamford.hackathon.ui.main.viewholder

import com.stamford.hackathon.core.ItemViewHolder
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.databinding.ViewHolderItemListingBinding

class ItemListingViewHolder(private val binding: ViewHolderItemListingBinding) : ItemViewHolder<ItemListingUiModel.ItemUiModel>(binding.root){

    override fun fillData(data: ItemListingUiModel.ItemUiModel, position: Int) {
        binding.itemNameTextView.text = data.title
        binding.itemPriceTextView.text = "$1000"
    }
}