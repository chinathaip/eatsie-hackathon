package com.stamford.hackathon.data

import com.stamford.hackathon.core.model.server.ItemListing
import com.stamford.hackathon.domain.GetFoodRepository
import retrofit2.Response

class GetFoodRepositoryImpl(private val dataSource: FoodDataSource) : GetFoodRepository {

    override suspend fun getListing(): Response<ItemListing> {
        return dataSource.getListing()
    }

    override suspend fun getSortedListing(type: String): Response<ItemListing> {
        return dataSource.getSortedListing(type)
    }
}