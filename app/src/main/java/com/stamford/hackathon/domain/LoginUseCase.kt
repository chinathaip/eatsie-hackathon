package com.stamford.hackathon.domain

import com.stamford.hackathon.core.BaseUseCase
import com.stamford.hackathon.core.model.server.User
import retrofit2.Response

class LoginUseCase(private val repository: GetFoodRepository) :
    BaseUseCase<LoginUseCase.Param, User?>() {

    override suspend fun execute(parameter: Param?): Response<User?> {
        return repository.login(parameter?.body)
    }

    data class Param(val body: Map<String, String>)
}