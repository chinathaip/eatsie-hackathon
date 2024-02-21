package com.stamford.hackathon.domain

import com.stamford.hackathon.core.BaseUseCase
import retrofit2.Response


class ClientPickupConfirmUseCase(private val repository: GetFoodRepository) :
    BaseUseCase<ClientPickupConfirmUseCase.Param, Any?>() {

    override suspend fun execute(parameter: Param?): Response<Any?> {
        return repository.confirmPickup(parameter?.body)
    }

    data class Param(val body: Map<String, String>)
}