package com.stamford.hackathon.ui.profile.viewholder

import coil.api.load
import com.stamford.hackathon.R
import com.stamford.hackathon.core.ItemViewHolder
import com.stamford.hackathon.core.Units
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.databinding.ViewHolderPendingOrderBinding

class PendingOrderViewHolder(private val binding: ViewHolderPendingOrderBinding) :
    ItemViewHolder<ItemListingUiModel.ItemUiModel>(binding.root) {

    override fun fillData(data: ItemListingUiModel.ItemUiModel, position: Int) {
        binding.itemNameTextView.text =
            itemView.context.getString(
                R.string.item_name_title,
                data.title,
                data.weight,
                Units.assign(data.category)
            )
        binding.itemPriceTextView.text = data.price
        binding.itemStatusTextView.text =
            itemView.context.getString(R.string.item_order_status, data.status)
        binding.itemImageView.load(data.imageUrl) {
            error(R.drawable.placeholder_image)
        }
    }
}