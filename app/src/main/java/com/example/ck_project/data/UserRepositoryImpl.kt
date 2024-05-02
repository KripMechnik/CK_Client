package com.example.ck_project.data

import com.example.ck_project.data.dto.UserDto
import com.example.ck_project.data.network.RetrofitFactory
import com.example.ck_project.data.utils.CallToConsumer
import com.example.ck_project.domain.UserRepository
import com.example.ck_project.domain.entity.EnterUserEntity
import com.example.ck_project.domain.entity.RegisterUserEntity
import com.example.ck_project.domain.entity.Status
import com.example.ck_project.domain.entity.UserEntity
import java.util.function.Consumer

object UserRepositoryImpl : UserRepository {
    private val userApi = RetrofitFactory.getUserApi()

    override fun loginUser(enterUser: EnterUserEntity, callback: Consumer<Status<UserEntity?>?>) {
        userApi.loginUser(enterUser).enqueue(CallToConsumer(
            callback,
            CallToConsumer.Mapper {
                user: UserDto ->
                val login = user.login
                val number = user.number
                val token = user.token
                return@Mapper UserEntity(
                    login = login,
                    token = token,
                    number = number
                )
            }
        ))
    }

    override fun registerUser(registerUser: RegisterUserEntity, callback: Consumer<Status<UserEntity?>?>) {
        userApi.registerUser(registerUser).enqueue(CallToConsumer(
            callback,
            CallToConsumer.Mapper {
                    user: UserDto ->
                val login = user.login
                val number = user.number
                val token = user.token
                return@Mapper UserEntity(
                    login = login,
                    token = token,
                    number = number
                )
            }
        ))
    }
}