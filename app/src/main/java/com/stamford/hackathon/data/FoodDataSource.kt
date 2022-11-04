package com.stamford.hackathon.data

import com.stamford.hackathon.data.model.Coins
import retrofit2.http.GET

interface FoodDataSource{

    @GET("coins")
    suspend fun getFood(): Coins
}