package com.example.ck_project.data.dto

import com.google.gson.annotations.SerializedName

class UserDto {
    @SerializedName("login")
    var login: String? = null

    @SerializedName("password")
    var password: String? = null

    @SerializedName("number")
    var number: String? = null
}