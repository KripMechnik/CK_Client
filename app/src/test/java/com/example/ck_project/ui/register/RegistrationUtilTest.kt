package com.example.ck_project.ui.register

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {
    @Test
    fun `login or password or number is empty returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "",
            "89166386901"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `login is shorter than 8 symbols returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "mister",
            "1234345566asdf",
            "89166386901"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `password contains 8 digits and 3 letters returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "normallogin",
            "1234asffg",
            "89166386901"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `number length is more than 15 returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "normallogin",
            "normalpassword1242344567",
            "891663869012345432355"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `correct input returns true`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "normallogin",
            "normalpassword1242344567",
            "89166386901"
        )
        assertThat(result).isTrue()
    }
}