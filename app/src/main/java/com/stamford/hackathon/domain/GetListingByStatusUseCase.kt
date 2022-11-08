package com.stamford.hackathon.domain

import com.stamford.hackathon.core.BaseUseCase
import com.stamford.hackathon.core.model.server.ItemListing
import retrofit2.Response

class GetListingByStatusUseCase(private val repository: GetFoodRepository) :
    BaseUseCase<String, ItemListing?>() {

    override suspend fun execute(parameter: String?): Response<ItemListing?> {
        return repository.getListingByStatus(parameter)
    }
}