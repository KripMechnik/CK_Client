package com.example.ck_project.data.network

import com.example.ck_project.data.source.ProductApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.98:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getProductApi(): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }
}