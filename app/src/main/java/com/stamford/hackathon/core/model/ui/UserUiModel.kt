package com.stamford.hackathon.core.model.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserUiModel(val userId: String, val username: String, val profileImgUrl: String) :
    Parcelable