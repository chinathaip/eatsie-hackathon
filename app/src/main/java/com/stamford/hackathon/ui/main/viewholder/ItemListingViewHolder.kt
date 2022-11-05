package com.stamford.hackathon.ui.main.viewholder

import com.stamford.hackathon.R
import com.stamford.hackathon.core.ItemViewHolder
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.databinding.ViewHolderItemListingBinding
import com.stamford.hackathon.core.OnItemListingClick

class ItemListingViewHolder(
    private val binding: ViewHolderItemListingBinding,
    onItemClickListener: OnItemListingClick
) : ItemViewHolder<ItemListingUiModel.ItemUiModel>(binding.root) {

    private var item: ItemListingUiModel.ItemUiModel? = null

    init {
        itemView.setOnClickListener { onItemClickListener.onItemClick(item) }
    }

    override fun fillData(data: ItemListingUiModel.ItemUiModel, position: Int) {
        this.item = data
        binding.itemNameTextView.text =
            itemView.context.getString(R.string.item_name_title, data.title, data.weight)
        binding.itemBoughtDateTextView.text = itemView.context.getString(
            R.string.item_bought_date, data.boughtTime
        )
        binding.itemExpiredDateTextView.text =
            itemView.context.getString(R.string.item_expired_date, data.expiredTime)
        binding.itemPriceTextView.text = data.price
        binding.itemQuantityTextView.text = itemView.context.getString(R.string.item_quantity, data.amount)
    }
}