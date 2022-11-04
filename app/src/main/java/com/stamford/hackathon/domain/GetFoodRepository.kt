package com.stamford.hackathon.domain

import com.stamford.hackathon.data.model.Coins

interface GetFoodRepository {

    suspend fun getFood(): Coins
}