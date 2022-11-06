package com.stamford.hackathon.domain

import com.stamford.hackathon.core.model.server.ItemListing

class GetListingUseCase(private val repository: GetFoodRepository) {

    suspend operator fun invoke(): Result<ItemListing?> {
        repository.getListing().let {
            return if (it.isSuccessful) {
                Result.success(it.body())
            } else {
                Result.failure(Exception(it.errorBody().toString()))
            }
        }
    }
}