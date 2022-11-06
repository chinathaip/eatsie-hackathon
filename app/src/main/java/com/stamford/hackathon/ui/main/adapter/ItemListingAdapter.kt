package com.stamford.hackathon.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.stamford.hackathon.core.ItemViewHolder
import com.stamford.hackathon.core.model.ui.ItemListingType
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.databinding.ViewHolderHeaderBinding
import com.stamford.hackathon.databinding.ViewHolderItemListingBinding
import com.stamford.hackathon.ui.main.OnItemListingClick
import com.stamford.hackathon.ui.main.viewholder.HeaderViewHolder
import com.stamford.hackathon.ui.main.viewholder.ItemListingViewHolder

class ItemListingAdapter(private val onItemClickListener: OnItemListingClick) :
    ListAdapter<ItemListingUiModel, ItemViewHolder<*>>(DiffCallBack()) {

    override fun getItemViewType(position: Int): Int = getItem(position).type

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<*> {
        return when (viewType) {
            ItemListingType.TYPE_ITEM -> ItemListingViewHolder(
                ViewHolderItemListingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), onItemClickListener
            )
            else -> HeaderViewHolder(
                ViewHolderHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder<*>, position: Int) {
        (holder as ItemViewHolder<ItemListingUiModel>).fillData(getItem(position), position)
    }

    class DiffCallBack() : DiffUtil.ItemCallback<ItemListingUiModel>() {
        override fun areItemsTheSame(
            oldItem: ItemListingUiModel,
            newItem: ItemListingUiModel
        ): Boolean {
            return when {
                oldItem is ItemListingUiModel.ItemUiModel && newItem is ItemListingUiModel.ItemUiModel -> oldItem.title == newItem.title
                else -> false
            }
        }

        override fun areContentsTheSame(
            oldItem: ItemListingUiModel,
            newItem: ItemListingUiModel
        ): Boolean {
            return when {
                oldItem is ItemListingUiModel.ItemUiModel && newItem is ItemListingUiModel.ItemUiModel -> oldItem == newItem
                else -> false
            }
        }

    }
}