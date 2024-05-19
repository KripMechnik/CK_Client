package com.example.ck_project.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ck_project.domain.entity.FullItemEntity
import kotlinx.coroutines.launch
import java.util.Dictionary

class CartViewModel : ViewModel() {
    val products = MutableLiveData<MutableMap<FullItemEntity, Int>>(mutableMapOf())
    val readyProducts = MutableLiveData<MutableList<MutableMap.MutableEntry<FullItemEntity, Int>>>()
    fun addItem(item: FullItemEntity){
        if (products.value!![item] == null){
            products.value!![item] = 1
        } else {
            products.value!![item] = products.value!![item]!! + 1
        }
        readyProducts.value = toListData(products.value!!)
    }
    fun removeItem(item: FullItemEntity){
        if (products.value!![item] != null){
            if (products.value!![item] == 1){
                products.value!!.remove(item)
            } else {
                products.value!![item] = products.value!![item]!! - 1
            }
        }
        readyProducts.value = toListData(products.value!!)
    }
    fun toListData(newData: MutableMap<FullItemEntity, Int>): MutableList<MutableMap.MutableEntry<FullItemEntity, Int>> {
        val entriesList = newData.entries.toMutableList()
        return entriesList
    }
}