package com.stamford.hackathon.core.model.server

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("data")
    val userinfo: UserInfo,
)