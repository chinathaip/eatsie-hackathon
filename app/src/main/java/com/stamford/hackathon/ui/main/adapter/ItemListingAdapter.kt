package com.stamford.hackathon.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.stamford.hackathon.core.ItemViewHolder
import com.stamford.hackathon.core.OnCategoryClick
import com.stamford.hackathon.core.OnItemListingClick
import com.stamford.hackathon.core.model.ui.ItemListingType
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.databinding.ViewHolderCategoryContainerBinding
import com.stamford.hackathon.databinding.ViewHolderHeaderBinding
import com.stamford.hackathon.databinding.ViewHolderItemListingBinding
import com.stamford.hackathon.databinding.ViewHolderLoadingBinding
import com.stamford.hackathon.ui.main.viewholder.CategoryContainerViewHolder
import com.stamford.hackathon.ui.main.viewholder.HeaderViewHolder
import com.stamford.hackathon.ui.main.viewholder.ItemListingViewHolder
import com.stamford.hackathon.ui.main.viewholder.LoadingViewHolder

class ItemListingAdapter(
    private val onItemClickListener: OnItemListingClick,
    private val onCategoryClickListener: OnCategoryClick
) :
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
            ItemListingType.TYPE_CATEGORY -> CategoryContainerViewHolder(
                ViewHolderCategoryContainerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                ), onCategoryClickListener
            )
            ItemListingType.TYPE_LOADING -> LoadingViewHolder(
                ViewHolderLoadingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
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
                oldItem is ItemListingUiModel.GroupHeader && newItem is ItemListingUiModel.GroupHeader -> oldItem.name == newItem.name
                oldItem is ItemListingUiModel.ItemUiModel && newItem is ItemListingUiModel.ItemUiModel -> oldItem.title == newItem.title
                oldItem is ItemListingUiModel.ListOfCategory && newItem is ItemListingUiModel.ListOfCategory -> oldItem.categories.size == newItem.categories.size
                else -> false
            }
        }

        override fun areContentsTheSame(
            oldItem: ItemListingUiModel,
            newItem: ItemListingUiModel
        ): Boolean {
            return when {
                oldItem is ItemListingUiModel.GroupHeader && newItem is ItemListingUiModel.GroupHeader -> oldItem == newItem
                oldItem is ItemListingUiModel.ItemUiModel && newItem is ItemListingUiModel.ItemUiModel -> oldItem == newItem
                oldItem is ItemListingUiModel.ListOfCategory && newItem is ItemListingUiModel.ListOfCategory -> oldItem == newItem
                else -> false
            }
        }

    }
}