package com.stamford.hackathon.domain

import com.stamford.hackathon.core.model.server.ItemListing

class GetListingByStatusUseCase(private val repository: GetFoodRepository) {

    suspend operator fun invoke(status: String): Result<ItemListing?> {
        repository.getListingByStatus(status).let {
            return if (it.isSuccessful) {
                Result.success(it.body())
            } else {
                Result.failure(Exception(it.errorBody().toString()))
            }
        }
    }
}