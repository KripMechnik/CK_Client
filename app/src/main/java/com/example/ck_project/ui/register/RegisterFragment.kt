package com.example.ck_project.ui.register

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
import com.example.ck_project.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[RegisterViewModel::class.java]
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        sharedPreferences = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
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
                if (viewModel.validateData()){
                    viewModel.loginUser()
                } else {
                    Toast.makeText(requireContext(), "Некорретные данные", Toast.LENGTH_LONG).show()
                }

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
                findNavController().navigate(R.id.action_navigation_register_to_navigation_profile)
            }

            if (state.errorMessage != null){
                Toast.makeText(requireContext(), "Пользователь уже зарегистрирован", Toast.LENGTH_SHORT).show()
            }

        })
    }


}