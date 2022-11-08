package com.stamford.hackathon.domain


class ClientPickupConfirmUseCase(private val repository: GetFoodRepository) {

    suspend operator fun invoke(body: Map<String, String>): Result<Any?> {
        repository.confirmPickup(body).let {
            return if (it.isSuccessful) {
                Result.success(it)
            } else {
                Result.failure(Exception(it.errorBody().toString()))
            }
        }
    }
}