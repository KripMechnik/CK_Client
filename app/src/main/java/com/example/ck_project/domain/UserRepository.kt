package com.example.ck_project.domain

import com.example.ck_project.domain.entity.EnterUserEntity
import com.example.ck_project.domain.entity.RegisterUserEntity
import com.example.ck_project.domain.entity.Status
import com.example.ck_project.domain.entity.UserEntity
import java.util.function.Consumer

interface UserRepository {

    fun loginUser(enterUser: EnterUserEntity, callback: Consumer<Status<UserEntity?>?>)

    fun registerUser(registerUser: RegisterUserEntity, callback: Consumer<Status<UserEntity?>?>)
}