package com.example.ck_project.data.dto

import com.google.gson.annotations.SerializedName

class ProductDto {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("image")
    var image: String? = null

    @SerializedName("price")
    var price: String? = null
}
