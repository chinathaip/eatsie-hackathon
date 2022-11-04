package com.stamford.hackathon.domain

import com.stamford.hackathon.data.model.Coins

class GetFoodUseCase(private val repository: GetFoodRepository) {

    suspend operator fun invoke(): Coins {
        return repository.getFood()
    }
}