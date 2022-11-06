package com.stamford.hackathon.domain

import com.stamford.hackathon.core.model.server.ItemListing
import retrofit2.Response

interface GetFoodRepository {

    suspend fun getListing(): Response<ItemListing>

    suspend fun getSortedListing(type: String): Response<ItemListing>

    suspend fun confirmPickup(body: Map<String, String>): Response<Any?>
}