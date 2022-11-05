package com.stamford.hackathon.ui.main.viewholder

import com.stamford.hackathon.core.ItemViewHolder
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.databinding.ViewHolderItemListingBinding
import com.stamford.hackathon.ui.main.OnItemListingClick

class ItemListingViewHolder(
    private val binding: ViewHolderItemListingBinding,
    onItemClickListener: OnItemListingClick
) : ItemViewHolder<ItemListingUiModel.ItemUiModel>(binding.root) {

    private var item: ItemListingUiModel.ItemUiModel? = null

    init {
        itemView.setOnClickListener{ onItemClickListener.onItemClick(item)}
    }

    override fun fillData(data: ItemListingUiModel.ItemUiModel, position: Int) {
        this.item = data
        binding.itemNameTextView.text = data.title
        binding.itemPriceTextView.text = data.price
    }
}