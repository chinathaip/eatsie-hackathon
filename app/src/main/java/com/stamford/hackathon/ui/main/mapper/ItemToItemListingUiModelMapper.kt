package com.stamford.hackathon.ui.main.mapper

import coil.map.Mapper
import com.stamford.hackathon.core.Const
import com.stamford.hackathon.core.model.server.Data
import com.stamford.hackathon.core.model.ui.ItemListingUiModel

object ItemToItemListingUiModelMapper : Mapper<Data, ItemListingUiModel.ItemUiModel> {
    override fun map(data: Data): ItemListingUiModel.ItemUiModel {
        return ItemListingUiModel.ItemUiModel(
            data.title ?: "",
            data.description ?: "",
            formatDate(data.boughtDate ?: ""),
            formatDate(data.expiryDate ?: ""),
            ("฿ " + data.price?.toString()),
            data.amount?.toString() ?: "",
            data.weight?.toString() ?: "",
            data.status ?: Const.STATUS_AVAILABLE,
            data.category ?: Const.CATEGORY_DIARIES
        )
    }

    private fun formatDate(date: String): String {
        return if (date.isNotBlank()) {
            date.substring(0, date.indexOf("T"))
        } else ""
    }
}