package com.example.ck_project.ui.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ck_project.R
import com.example.ck_project.databinding.FragmentProfileBinding
import kotlin.math.log

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        sharedPreferences = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val login = sharedPreferences.getString("login", "")
        val number = sharedPreferences.getString("number", "")

        if (login!!.isNotEmpty() && number!!.isNotEmpty()){
            val formattedNumber = formatPhoneNumber(number)
            binding.tvLogin.text = "Логин: ${login}"
            binding.tvNumber.text = "Номер телефона: ${formattedNumber}"
        }

        binding.tvExit.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putString("token", "")
            editor.putString("login", "")
            editor.putString("number", "")
            editor.apply()
            findNavController().navigate(R.id.action_navigation_profile_to_navigation_login)
        }

    }


    private fun formatPhoneNumber(phoneNumber: String): String {
        val normalizedNumber = phoneNumber.replace("[^0-9]".toRegex(), "")
        val formattedNumber = buildString {
            append("+")
            for (i in 1 until normalizedNumber.length) {
                when (i) {
                    2 -> append(" (")
                    5 -> append(") ")
                    8 -> append("-")
                    10 -> append("-")
                }
                append(normalizedNumber[i - 1])
            }
            append(normalizedNumber.last())
        }
        return formattedNumber
    }
}