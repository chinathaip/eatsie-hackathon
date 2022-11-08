package com.stamford.hackathon.domain

class LoginUseCase(private val repository: GetFoodRepository) {

    suspend operator fun invoke(body: Map<String, String>): Result<Any?> {
        repository.login(body).let {
            return if (it.isSuccessful) {
                Result.success(it.body())
            } else {
                Result.failure(Exception(it.errorBody().toString()))
            }
        }
    }
}