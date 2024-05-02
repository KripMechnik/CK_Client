package com.example.ck_project.data.source

import com.example.ck_project.data.dto.UserDto
import com.example.ck_project.domain.entity.EnterUserEntity
import com.example.ck_project.domain.entity.RegisterUserEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("login")
    fun loginUser(@Body enterUserEntity: EnterUserEntity): Call<UserDto>

    @POST("register")
    fun registerUser(@Body registerUserEntity: RegisterUserEntity): Call<UserDto>
}