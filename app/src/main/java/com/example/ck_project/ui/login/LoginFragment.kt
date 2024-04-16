package com.example.ck_project.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ck_project.R
import com.example.ck_project.databinding.FragmentLoginBinding
import com.example.ck_project.databinding.FragmentRegisterBinding

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginButton = binding.loginButton
        val registerButton = binding.registerButton
        val etLogin = binding.etLogin
        val etPassword = binding.etPassword


        loginButton.setOnClickListener{
            if(!etLogin.text?.isEmpty()!! && !etPassword.text?.isEmpty()!!){
                viewModel.login.value = etLogin.text.toString()
                viewModel.password.value = etPassword.text.toString()

                //Запрос на сервер с отправлением данных пользователя для входа в систему
                findNavController().navigate(R.id.action_navigation_login_to_navigation_profile)
            }
        }

        registerButton.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_login_to_navigation_register)
        }
    }

}