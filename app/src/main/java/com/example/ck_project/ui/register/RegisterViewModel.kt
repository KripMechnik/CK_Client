package com.example.ck_project.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ck_project.data.UserRepositoryImpl
import com.example.ck_project.domain.LoginUserUseCase
import com.example.ck_project.domain.RegisterUserUseCase
import com.example.ck_project.domain.entity.EnterUserEntity
import com.example.ck_project.domain.entity.RegisterUserEntity
import com.example.ck_project.domain.entity.Status
import com.example.ck_project.domain.entity.UserEntity

class RegisterViewModel : ViewModel() {
    val mutableStateLiveData: MutableLiveData<RegisterViewModel.State> = MutableLiveData()
    val registerUserUseCase: RegisterUserUseCase = RegisterUserUseCase(UserRepositoryImpl)
    val login = MutableLiveData("")
    val password = MutableLiveData("")
    val number = MutableLiveData("")
    fun validateData():Boolean{
        return RegistrationUtil.validateRegistrationInput(
            login.value!!,
            password.value!!,
            number.value!!
        )
    }
    fun loginUser() {
        mutableStateLiveData.value = State(null, null, true)
        registerUserUseCase.execute(RegisterUserEntity(login.value!!, password.value!!, number.value!!)) { status ->
            mutableStateLiveData.postValue(fromStatus(status))
        }
    }
    private fun fromStatus(status: Status<UserEntity?>?): RegisterViewModel.State {
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