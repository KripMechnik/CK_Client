package com.example.ck_project.domain

import com.example.ck_project.domain.entity.FullItemEntity
import com.example.ck_project.domain.entity.Status
import java.util.function.Consumer

interface ProductRepository {
    fun getAllProducts(callback: Consumer<Status<List<FullItemEntity>?>?>)

    fun getProductById(id: String, callback: Consumer<Status<FullItemEntity?>?>)
}