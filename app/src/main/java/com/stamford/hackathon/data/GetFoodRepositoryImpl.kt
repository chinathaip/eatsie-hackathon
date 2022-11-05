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
                        price = 56.7,
                        weightPerPortion = 30.5F,
                        boughtDate = Timestamp(123L),
                        expiredDate = Timestamp(456L),
                    ),
                    Item(
                        title = "test item 2",
                        description = "description of item 2adadadadwadawdhawiudawhiduahwdiauhwdiwauhdaiwhdaiwdhawiuhdawidhawiudhaiduhawuidawhdiawuhdaiwuhdawiuhdawihduawiudh",
                        category = Const.CATEGORY_MEAT,
                        amount = 3,
                        price = 43.6,
                        weightPerPortion = 30.5F,
                        boughtDate = Timestamp(123L),
                        expiredDate = Timestamp(456L),
                    ),
                    Item(
                        title = "test item 3",
                        description = "description of item 3",
                        category = Const.CATEGORY_MEAT,
                        amount = 3,
                        price = 562.7,
                        weightPerPortion = 30.5F,
                        boughtDate = Timestamp(123L),
                        expiredDate = Timestamp(456L),
                    ),
                    Item(
                        title = "test item 4",
                        description = "description of item 4",
                        category = Const.CATEGORY_MEAT,
                        amount = 3,
                        price = 5666.7,
                        weightPerPortion = 30.5F,
                        boughtDate = Timestamp(123L),
                        expiredDate = Timestamp(456L),
                    ),
                    Item(
                        title = "test item 5",
                        description = "description of item 5",
                        category = Const.CATEGORY_MEAT,
                        amount = 3,
                        price = 156.7,
                        weightPerPortion = 30.5F,
                        boughtDate = Timestamp(123L),
                        expiredDate = Timestamp(456L),
                    ),
                    Item(
                        title = "test item 6",
                        description = "description of item 6",
                        category = Const.CATEGORY_MEAT,
                        amount = 3,
                        price = 5656.7,
                        weightPerPortion = 30.5F,
                        boughtDate = Timestamp(123L),
                        expiredDate = Timestamp(456L),
                    ),
                    Item(
                        title = "test item 7",
                        description = "description of item 7",
                        category = Const.CATEGORY_MEAT,
                        amount = 3,
                        price = 6656.7,
                        weightPerPortion = 30.5F,
                        boughtDate = Timestamp(123L),
                        expiredDate = Timestamp(456L),
                    ),
                    Item(
                        title = "test item 8",
                        description = "description of item 8",
                        category = Const.CATEGORY_MEAT,
                        amount = 3,
                        price = 7756.7,
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