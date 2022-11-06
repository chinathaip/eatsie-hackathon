package com.stamford.hackathon.ui.main.mapper

import coil.map.Mapper
import com.stamford.hackathon.core.Const
import com.stamford.hackathon.core.model.server.Item
import com.stamford.hackathon.core.model.ui.ItemListingUiModel

object ItemToItemListingUiModelMapper : Mapper<Item, ItemListingUiModel.ItemUiModel> {
    override fun map(data: Item): ItemListingUiModel.ItemUiModel {
        return ItemListingUiModel.ItemUiModel(
            data.title ?: "",
            data.description ?: "",
            data.expiredDate?.toString() ?: "",
            data.amount ?: 0,
            data.status ?: Const.STATUS_AVAILABLE,
            data.category ?: Const.CATEGORY_DIARIES
        )
    }
}