package com.stamford.hackathon.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.stamford.hackathon.core.ItemViewHolder
import com.stamford.hackathon.core.OnItemListingClick
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.databinding.ViewHolderItemListingBinding
import com.stamford.hackathon.ui.main.viewholder.ItemListingViewHolder

class SearchResultAdapter(private val onItemClickListener: OnItemListingClick) :
    ListAdapter<ItemListingUiModel.ItemUiModel, ItemViewHolder<ItemListingUiModel.ItemUiModel>>(
        DiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ItemViewHolder<ItemListingUiModel.ItemUiModel> {
        return ItemListingViewHolder(
            ViewHolderItemListingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onItemClickListener
        )
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder<ItemListingUiModel.ItemUiModel>, position: Int
    ) = holder.fillData(getItem(position), position)

    class DiffCallback : DiffUtil.ItemCallback<ItemListingUiModel.ItemUiModel>() {
        override fun areItemsTheSame(
            oldItem: ItemListingUiModel.ItemUiModel,
            newItem: ItemListingUiModel.ItemUiModel
        ): Boolean = oldItem.itemId == newItem.itemId

        override fun areContentsTheSame(
            oldItem: ItemListingUiModel.ItemUiModel,
            newItem: ItemListingUiModel.ItemUiModel
        ): Boolean = oldItem == newItem

    }
}