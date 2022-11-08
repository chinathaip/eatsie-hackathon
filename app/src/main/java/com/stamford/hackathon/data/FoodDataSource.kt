package com.stamford.hackathon.data

import com.stamford.hackathon.core.model.server.ItemListing
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query
import retrofit2.http.QueryName

interface FoodDataSource{

    @GET("listings")
    suspend fun getListing() : Response<ItemListing?>

    @GET("listings/sorted")
    suspend fun getSortedListing(
        @Query("category")
        type: String?
    ) : Response<ItemListing?>

    @GET("listings/get_status")
    suspend fun getListingByStatusUseCase(
        @Query("status")
        status: String?
    ) : Response<ItemListing?>

    @PUT("listings/user_confirm")
    suspend fun confirmPickup(
        @Body
        body: Map<String, String>?
    ) : Response<Any?>

    @POST("users/login")
    suspend fun login(
        @Body
        body: Map<String, String>
    ) : Response<Any>
}