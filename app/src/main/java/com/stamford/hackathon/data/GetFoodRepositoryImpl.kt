package com.stamford.hackathon.data

import com.stamford.hackathon.data.model.Coins
import com.stamford.hackathon.domain.GetFoodRepository

class GetFoodRepositoryImpl(private val dataSource: FoodDataSource) : GetFoodRepository {

    override suspend fun getFood() : Coins {
        return dataSource.getFood()
    }
}