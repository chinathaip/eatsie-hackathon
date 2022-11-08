package com.stamford.hackathon.ui.main.viewholder

import com.stamford.hackathon.core.ItemViewHolder
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.databinding.ViewHolderHeaderBinding

class HeaderViewHolder(private val binding: ViewHolderHeaderBinding) :
    ItemViewHolder<ItemListingUiModel.GroupHeader>(binding.root) {

    override fun fillData(data: ItemListingUiModel.GroupHeader, position: Int) {
        binding.gruopHeaderTextView.text = data.name
    }

}