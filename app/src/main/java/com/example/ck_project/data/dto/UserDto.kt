package com.example.ck_project.data.dto

import com.google.gson.annotations.SerializedName

class UserDto {
    @SerializedName("login")
    var login: String? = null

    @SerializedName("token")
    var token: String? = null

    @SerializedName("number")
    var number: String? = null
}