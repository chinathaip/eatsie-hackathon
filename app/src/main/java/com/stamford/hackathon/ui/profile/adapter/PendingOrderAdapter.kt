package com.stamford.hackathon.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.stamford.hackathon.core.ItemViewHolder
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.databinding.ViewHolderPendingOrderBinding
import com.stamford.hackathon.ui.profile.viewholder.PendingOrderViewHolder

class PendingOrderAdapter :
    ListAdapter<ItemListingUiModel.ItemUiModel, ItemViewHolder<ItemListingUiModel.ItemUiModel>>(
        DiffCallBack()
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder<ItemListingUiModel.ItemUiModel> {
        return PendingOrderViewHolder(
            ViewHolderPendingOrderBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder<ItemListingUiModel.ItemUiModel>,
        position: Int
    ) {
        holder.fillData(getItem(position), position)
    }

    class DiffCallBack : DiffUtil.ItemCallback<ItemListingUiModel.ItemUiModel>() {
        override fun areItemsTheSame(
            oldItem: ItemListingUiModel.ItemUiModel,
            newItem: ItemListingUiModel.ItemUiModel
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: ItemListingUiModel.ItemUiModel,
            newItem: ItemListingUiModel.ItemUiModel
        ): Boolean {
            return oldItem == newItem
        }

    }
}