package com.stamford.hackathon.domain

import com.stamford.hackathon.core.BaseUseCase
import com.stamford.hackathon.core.model.server.ItemListing
import retrofit2.Response

class GetListingUseCase(private val repository: GetFoodRepository) :
    BaseUseCase<Any, ItemListing?>() {

    override suspend fun execute(parameter: Any?): Response<ItemListing?> {
        return repository.getListing()
    }
}