package com.example.ck_project.data

import com.example.ck_project.domain.ProductRepository
import com.example.ck_project.domain.entity.FullItemEntity
import com.example.ck_project.domain.entity.Status
import com.example.ck_project.data.network.RetrofitFactory
import java.util.function.Consumer

object ProductRepositoryImpl : ProductRepository {
    private val productApi = RetrofitFactory.getProductApi()
    override fun getAllProducts(callback: Consumer<Status<List<FullItemEntity?>?>?>) {

    }

    override fun getProductById(id: String, callback: Consumer<Status<FullItemEntity?>?>) {

    }
}