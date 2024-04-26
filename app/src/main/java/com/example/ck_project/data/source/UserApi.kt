package com.example.ck_project.data.source

import com.example.ck_project.data.dto.UserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("get_user")
    fun getUserById(@Query("token") token: String): Call<UserDto>
}