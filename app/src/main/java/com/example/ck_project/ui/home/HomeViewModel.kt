package com.example.ck_project.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ck_project.data.ProductRepositoryImpl
import com.example.ck_project.domain.GetAllProductsUseCase
import com.example.ck_project.domain.GetProductUseCase
import com.example.ck_project.domain.entity.FullItemEntity
import com.example.ck_project.domain.entity.Status

class HomeViewModel : ViewModel() {
    val mutableStateLiveData: MutableLiveData<State> = MutableLiveData()
    val stateLiveData: LiveData<State> = mutableStateLiveData
    val getAllProductsUseCase: GetAllProductsUseCase = GetAllProductsUseCase(ProductRepositoryImpl)
    init {
        updateList()
    }
    fun updateList() {
        mutableStateLiveData.value = State(null, null, true)
        getAllProductsUseCase.execute { status ->
            mutableStateLiveData.postValue(fromStatus(status))
        }
    }
    private fun fromStatus(status: Status<List<FullItemEntity>?>?): State {
        return State(
            if (status!!.errors != null) status.errors!!.localizedMessage else null,
            status.value,
            false
        )
    }
    inner class State(
        val errorMessage: String?,
        val items: List<FullItemEntity>?,
        val isLoading: Boolean
    )
}