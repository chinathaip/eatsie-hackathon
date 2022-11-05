package com.stamford.hackathon.core.model.ui

sealed class ItemListingUiModel(val type: Int) {

    data class ItemUiModel(
        val title: String,
        val description: String,
        val expiredTime: String,
        val price: String,
        val amount: Int,
        val status: Int,
        val category: Int
    ): ItemListingUiModel(ItemListingType.TYPE_ITEM)

    data class GroupHeader(val name: String) : ItemListingUiModel(ItemListingType.TYPE_HEADER)
}
