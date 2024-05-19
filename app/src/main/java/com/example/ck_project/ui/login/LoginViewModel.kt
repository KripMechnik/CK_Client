package com.example.ck_project.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ck_project.data.ProductRepositoryImpl
import com.example.ck_project.data.UserRepositoryImpl
import com.example.ck_project.domain.GetAllProductsUseCase
import com.example.ck_project.domain.LoginUserUseCase
import com.example.ck_project.domain.entity.EnterUserEntity
import com.example.ck_project.domain.entity.FullItemEntity
import com.example.ck_project.domain.entity.Status
import com.example.ck_project.domain.entity.UserEntity
import com.example.ck_project.ui.home.HomeViewModel

class LoginViewModel : ViewModel() {
    val mutableStateLiveData: MutableLiveData<LoginViewModel.State> = MutableLiveData()
    val loginUserUseCase: LoginUserUseCase = LoginUserUseCase(UserRepositoryImpl)
    val login = MutableLiveData("")
    val password = MutableLiveData("")
    fun loginUser() {
        mutableStateLiveData.value = State(null, null, true)
        loginUserUseCase.execute(EnterUserEntity(login.value!!, password.value!!)) { status ->
            mutableStateLiveData.postValue(fromStatus(status))
        }
    }
    private fun fromStatus(status: Status<UserEntity?>?): LoginViewModel.State {
        return State(
            if (status!!.errors != null) status.errors!!.localizedMessage else null,
            status.value,
            false
        )
    }
    inner class State(
        val errorMessage: String?,
        val user: UserEntity?,
        val isLoading: Boolean
    )
}