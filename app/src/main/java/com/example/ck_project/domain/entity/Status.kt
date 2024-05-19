package com.example.ck_project.domain.entity

data class Status<T>(
    val statusCode: Int,
    @JvmField val value: T?,
    @JvmField val errors: Throwable?
)
