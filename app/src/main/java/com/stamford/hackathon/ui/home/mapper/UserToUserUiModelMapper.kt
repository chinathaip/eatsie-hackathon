package com.stamford.hackathon.ui.home.mapper

import coil.map.Mapper
import com.stamford.hackathon.core.model.server.UserInfo
import com.stamford.hackathon.core.model.ui.UserUiModel

object UserToUserUiModelMapper : Mapper<UserInfo, UserUiModel> {
    override fun map(data: UserInfo): UserUiModel {
        return UserUiModel(
            data._id,
            data.username,
            data.profile.avatar
        )
    }
}