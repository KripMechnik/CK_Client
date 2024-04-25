package com.example.ck_project.data

import com.example.ck_project.data.dto.ProductDto
import com.example.ck_project.data.network.RetrofitFactory
import com.example.ck_project.data.utils.CallToConsumer
import com.example.ck_project.domain.ProductRepository
import com.example.ck_project.domain.entity.FullItemEntity
import com.example.ck_project.domain.entity.Status
import java.util.function.Consumer

object ProductRepositoryImpl : ProductRepository {
    private val productApi = RetrofitFactory.getProductApi()
    override fun getAllProducts(callback: Consumer<Status<List<FullItemEntity>?>?>) {
        productApi.getAllProducts().enqueue(
            CallToConsumer(
                callback
            ) { productsDto: List<ProductDto> ->
                val result = ArrayList<FullItemEntity>(productsDto.size)
                for (product in productsDto) {
                    val id = product.id
                    val name = product.name
                    val image = product.image
                    val price = product.price

                    if (id != null && name != null && image != null && price != null) {
                        result.add(FullItemEntity(id, name, image, price))
                    }
                }
                result
            })

    }

    override fun getProductById(id: String, callback: Consumer<Status<FullItemEntity?>?>) {
        productApi.getById(id).enqueue(CallToConsumer(
            callback,
            CallToConsumer.Mapper {product: ProductDto ->
            val resultId = product.id
            val name = product.name
            val image = product.image
            val price = product.price
            if (resultId != null && name != null && image != null && price != null) {
                return@Mapper FullItemEntity(
                    resultId,
                    name,
                    image,
                    price
                )
            } else {
                return@Mapper null
            }}
        ))
    }
}