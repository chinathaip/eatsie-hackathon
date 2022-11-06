package com.stamford.hackathon.domain

import android.util.Log
import com.stamford.hackathon.core.model.server.ItemListing

class GetSortedListingUseCase(private val repository: GetFoodRepository) {
    suspend operator fun invoke(type: String): Result<ItemListing?> {
        repository.getSortedListing(type).let {
            return if (it.isSuccessful) {
                Result.success(it.body())
            } else {
                Log.d("LOL", "failed")
                Result.failure(Exception(it.errorBody().toString()))
            }
        }
    }
}