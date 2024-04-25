package com.example.ck_project.domain

import com.example.ck_project.domain.entity.FullItemEntity
import com.example.ck_project.domain.entity.Status
import java.util.function.Consumer

class GetAllProductsUseCase(private val repo: ProductRepository) {
    fun execute(callback: Consumer<Status<List<FullItemEntity>?>?>) {
        repo.getAllProducts(callback)
    }
}