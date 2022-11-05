package com.stamford.hackathon.data

import com.stamford.hackathon.core.model.server.ItemListing
import retrofit2.Response
import retrofit2.http.GET

interface FoodDataSource{

    @GET("listings")
    suspend fun getListing() : Response<ItemListing>

}