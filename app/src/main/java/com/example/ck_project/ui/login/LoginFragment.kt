package com.example.ck_project.ui.login

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.ck_project.R
import com.example.ck_project.databinding.FragmentLoginBinding
import com.example.ck_project.databinding.FragmentRegisterBinding
import kotlinx.coroutines.*
import okhttp3.Dispatcher

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        sharedPreferences = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginButton = binding.loginButton
        val registerText = binding.tvToRegister
        val etLogin = binding.etLogin
        val etPassword = binding.etPassword


        loginButton.setOnClickListener{
            if(!etLogin.text?.isEmpty()!! && !etPassword.text?.isEmpty()!!){
                viewModel.login.value = etLogin.text.toString()
                viewModel.password.value = etPassword.text.toString()
                viewModel.loginUser()
            }
        }

        viewModel.mutableStateLiveData.observe(viewLifecycleOwner, Observer {state ->
            val isSuccess = !state.isLoading && state.errorMessage == null && state.user != null

            if (isSuccess){
                Log.i("RRR", state.user!!.token!!)
                val editor = sharedPreferences.edit()
                editor.putString("login", state.user.login)
                editor.putString("token", state.user.token)
                editor.putString("number", state.user.number)
                editor.apply()
                findNavController().navigate(R.id.action_navigation_login_to_navigation_profile)
            }

            if (state.errorMessage != null){
                Toast.makeText(requireContext(), "Пользователь не найден", Toast.LENGTH_SHORT).show()
            }

        })

        registerText.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_login_to_navigation_register)
        }
    }



}