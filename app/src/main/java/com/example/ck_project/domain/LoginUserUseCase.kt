package com.example.ck_project.domain

import com.example.ck_project.domain.entity.EnterUserEntity
import com.example.ck_project.domain.entity.Status
import com.example.ck_project.domain.entity.UserEntity
import java.util.function.Consumer

class LoginUserUseCase(private val repo: UserRepository) {
    fun execute(enterUser: EnterUserEntity, callback: Consumer<Status<UserEntity?>?>) {
        repo.loginUser(enterUser, callback)
    }
}