package com.stamford.hackathon.data

import com.stamford.hackathon.core.Const
import com.stamford.hackathon.core.model.server.Item
import com.stamford.hackathon.core.model.server.Listing
import com.stamford.hackathon.domain.GetFoodRepository
import java.sql.Timestamp

class GetFoodRepositoryImpl(private val dataSource: FoodDataSource) : GetFoodRepository {

    override suspend fun getListing(): Result<Listing> {
        return Result.success(
            Listing(
                listOf(
                    Item(
                        title = "test item 1",
                        description = "description of item 1",
                        category = Const.CATEGORY_MEAT,
                        amount = 3,
                        weightPerPortion = 30.5F,
                        boughtDate = Timestamp(123L),
                        expiredDate = Timestamp(456L),
                    ),
                    Item(
                        title = "test item 1",
                        description = "description of item 1",
                        category = Const.CATEGORY_MEAT,
                        amount = 3,
                        weightPerPortion = 30.5F,
                        boughtDate = Timestamp(123L),
                        expiredDate = Timestamp(456L),
                    ),
                    Item(
                        title = "test item 1",
                        description = "description of item 1",
                        category = Const.CATEGORY_MEAT,
                        amount = 3,
                        weightPerPortion = 30.5F,
                        boughtDate = Timestamp(123L),
                        expiredDate = Timestamp(456L),
                    ),
                    Item(
                        title = "test item 1",
                        description = "description of item 1",
                        category = Const.CATEGORY_MEAT,
                        amount = 3,
                        weightPerPortion = 30.5F,
                        boughtDate = Timestamp(123L),
                        expiredDate = Timestamp(456L),
                    ),
                    Item(
                        title = "test item 1",
                        description = "description of item 1",
                        category = Const.CATEGORY_MEAT,
                        amount = 3,
                        weightPerPortion = 30.5F,
                        boughtDate = Timestamp(123L),
                        expiredDate = Timestamp(456L),
                    ),
                    Item(
                        title = "test item 1",
                        description = "description of item 1",
                        category = Const.CATEGORY_MEAT,
                        amount = 3,
                        weightPerPortion = 30.5F,
                        boughtDate = Timestamp(123L),
                        expiredDate = Timestamp(456L),
                    ),
                    Item(
                        title = "test item 1",
                        description = "description of item 1",
                        category = Const.CATEGORY_MEAT,
                        amount = 3,
                        weightPerPortion = 30.5F,
                        boughtDate = Timestamp(123L),
                        expiredDate = Timestamp(456L),
                    ),
                    Item(
                        title = "test item 1",
                        description = "description of item 1",
                        category = Const.CATEGORY_MEAT,
                        amount = 3,
                        weightPerPortion = 30.5F,
                        boughtDate = Timestamp(123L),
                        expiredDate = Timestamp(456L),
                    )
                )
            )
        )
//        return Result.failure(Exception("failed"))
    }
}