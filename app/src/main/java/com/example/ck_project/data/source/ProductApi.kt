package com.example.ck_project.data.source

import com.example.ck_project.data.dto.ProductDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("get_products")
    fun getAllProducts(): Call<List<ProductDto>>

    @GET("get_products/get_product")
    fun getById(@Query("id") id: String): Call<ProductDto>
}
