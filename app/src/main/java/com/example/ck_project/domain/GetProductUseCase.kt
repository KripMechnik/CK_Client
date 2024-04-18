package com.example.ck_project.domain

import com.example.ck_project.domain.entity.FullItemEntity
import com.example.ck_project.domain.entity.Status
import java.util.function.Consumer

class GetProductUseCase (private val repo: ProductRepository){
    fun execute(id: String, callback: Consumer<Status<FullItemEntity?>?>) {
        repo.getProductById(id, callback)
    }
}