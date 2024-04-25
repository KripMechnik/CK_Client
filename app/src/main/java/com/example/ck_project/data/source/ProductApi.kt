package com.example.ck_project.data.source

import com.example.ck_project.data.dto.ProductDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("get_products")
    fun getAllProducts(): Call<List<ProductDto>>

    @GET("user/{id}")
    fun getById(@Path("id") id: String?): Call<ProductDto>
}
