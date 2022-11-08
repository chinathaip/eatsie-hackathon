package com.stamford.hackathon.data

import com.stamford.hackathon.core.model.server.ItemListing
import com.stamford.hackathon.core.model.server.User
import retrofit2.Response
import retrofit2.http.*

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
        body: Map<String, String>?
    ) : Response<User?>
}