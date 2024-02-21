package com.stamford.hackathon.core

import retrofit2.Response

abstract class BaseUseCase<I, R> {

    suspend operator fun invoke(item: I? = null): Result<R?> {
        execute(item).let {
            return if (it.isSuccessful) {
                Result.success(it.body())
            } else {
                Result.failure(Exception(it.errorBody().toString()))
            }
        }
    }

    abstract suspend fun execute(parameter: I?): Response<R>
}