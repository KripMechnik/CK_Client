package com.example.ck_project.domain

import com.example.ck_project.domain.entity.RegisterUserEntity
import com.example.ck_project.domain.entity.Status
import com.example.ck_project.domain.entity.UserEntity
import java.util.function.Consumer

class RegisterUserUseCase(private val repo: UserRepository) {
    fun execute(registerUser: RegisterUserEntity, callback: Consumer<Status<UserEntity?>?>) {
        repo.registerUser(registerUser, callback)
    }
}