package com.stamford.hackathon.core

import com.stamford.hackathon.core.model.ui.ItemListingUiModel

interface OnItemListingClick {
    fun onItemClick(data: ItemListingUiModel.ItemUiModel?)
}