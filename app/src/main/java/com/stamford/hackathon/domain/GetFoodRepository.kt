package com.stamford.hackathon.domain

import com.stamford.hackathon.core.model.server.Listing

interface GetFoodRepository {

    suspend fun getListing(): Result<Listing>
}