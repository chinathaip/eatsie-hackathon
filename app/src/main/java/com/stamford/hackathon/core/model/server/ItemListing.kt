package com.stamford.hackathon.core.model.server

import com.google.gson.annotations.SerializedName

data class ItemListing(
    @SerializedName("data")
    val items: List<Data>?,
    val success: Boolean
)