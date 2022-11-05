package com.stamford.hackathon.core.model.ui

import com.stamford.hackathon.core.Const

sealed class ItemListingUiModel(val type: Int) {

    data class ItemUiModel(
        val itemId: String,
        val title: String,
        val description: String,
        val boughtTime: String,
        val expiredTime: String,
        val price: String,
        val amount: String,
        val weight: String,
        val status: String,
        val category: String,
        val imageUrl: String
    ) : ItemListingUiModel(ItemListingType.TYPE_ITEM)

    data class GroupHeader(val name: String) : ItemListingUiModel(ItemListingType.TYPE_HEADER)

    data class Category(val name: String, val enum: String)

    data class ListOfCategory(val categories: List<Category>) :
        ItemListingUiModel(ItemListingType.TYPE_CATEGORY)

    companion object {
        fun createCategories(): ListOfCategory {
            return ListOfCategory(
                listOf(
                    Category("All", Const.CATEGORY_ALL),
                    Category("Dairies", Const.CATEGORY_DAIRIES),
                    Category("Fruits", Const.CATEGORY_FRUITS),
                    Category("Vegetables", Const.CATEGORY_VEGETABLE),
                    Category("Meat", Const.CATEGORY_MEAT)
                )
            )
        }
    }
}
