package com.stamford.hackathon.core.model.ui

sealed class ItemListingUiModel(val type: Int) {

    data class ItemUiModel(
        val title: String,
        val description: String,
        val boughtTime: String,
        val expiredTime: String,
        val price: String,
        val amount: String,
        val weight: String,
        val status: String,
        val category: String
    ): ItemListingUiModel(ItemListingType.TYPE_ITEM)

    data class GroupHeader(val name: String) : ItemListingUiModel(ItemListingType.TYPE_HEADER)
}
