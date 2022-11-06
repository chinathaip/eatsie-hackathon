package com.stamford.hackathon.ui.main

import com.stamford.hackathon.core.model.ui.ItemListingUiModel

interface OnItemListingClick {
    fun onItemClick(data: ItemListingUiModel.ItemUiModel?)
}