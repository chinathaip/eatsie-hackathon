package com.stamford.hackathon.core.model.server

data class UserInfo(
    val _id: String,
    val profile: Profile,
    val username: String
)