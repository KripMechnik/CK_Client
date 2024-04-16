package com.example.ck_project.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    val login = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    val number = MutableLiveData<String>()

    val email = MutableLiveData<String>()
}