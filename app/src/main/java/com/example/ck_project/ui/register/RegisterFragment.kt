package com.example.ck_project.ui.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ck_project.R
import com.example.ck_project.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity())[RegisterViewModel::class.java]
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerButton = binding.registerButton
        val etLogin = binding.etLogin
        val etPassword = binding.etPassword
        val etNumber = binding.etNumber

        registerButton.setOnClickListener{
            if(!etLogin.text?.isEmpty()!! && !etNumber.text?.isEmpty()!! && !etPassword.text?.isEmpty()!!){
                viewModel.login.value = etLogin.text.toString()
                viewModel.password.value = etPassword.text.toString()
                viewModel.number.value = etNumber.text.toString()

                //Запрос на сервер с отправлением данных пользователя для регистрации
                findNavController().navigate(R.id.action_navigation_register_to_navigation_profile)
            }
        }
    }

    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val phonePattern = """^\+[1-9]\d{10}$""".toRegex()
        return phonePattern.matches(phoneNumber)
    }

}