package com.example.ck_project.ui.register

object RegistrationUtil {
    fun validateRegistrationInput(
        login: String,
        password: String,
        number: String
    ) : Boolean{
        if (login.length < 8) {
            return false
        }
        if (password.count { it.isDigit()} < 8 || password.count{it.isLetter()} < 3){
            return false
        }
        if (login.isEmpty() || password.isEmpty() || number.isEmpty()){
            return false
        }
        if (number.length > 15){
            return false
        }
        return true
    }
}