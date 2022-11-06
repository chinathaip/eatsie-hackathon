package com.stamford.hackathon.domain

import com.stamford.hackathon.core.model.server.Listing

class GetFoodUseCase(private val repository: GetFoodRepository) {

    suspend operator fun invoke(): Result<Listing> {
        return repository.getListing()
            .onSuccess { it.items }
            .onFailure { throw it }
    }
}