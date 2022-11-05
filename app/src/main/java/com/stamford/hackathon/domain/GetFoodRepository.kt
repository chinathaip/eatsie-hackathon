package com.stamford.hackathon.domain

import com.stamford.hackathon.core.model.server.ItemListing
import retrofit2.Response

interface GetFoodRepository {

    suspend fun getListing(): Response<ItemListing>
}