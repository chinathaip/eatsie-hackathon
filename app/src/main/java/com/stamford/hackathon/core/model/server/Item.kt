package com.stamford.hackathon.core.model.server

import java.sql.Timestamp

data class Item(
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val category: Int? = null,
    val amount: Int? = null,
    val weightPerPortion: Float? = null,
    val status: Int? = null,
    val imageUrl: String? = null,
    val boughtDate: Timestamp? = null,
    val expiredDate: Timestamp? = null,
    val restaurantId: String? = null
)
