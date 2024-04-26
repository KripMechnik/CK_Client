package com.example.ck_project.domain

import com.example.ck_project.domain.entity.FullItemEntity
import com.example.ck_project.domain.entity.Status
import com.example.ck_project.domain.entity.UserEntity
import java.util.function.Consumer

interface UserRepository {
    fun getUser(token: String, callback: Consumer<Status<UserEntity>>)
}