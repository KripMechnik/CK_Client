package com.example.ck_project.ui

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ck_project.R
import com.example.ck_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        val profileMenuItem = navView.menu.findItem(R.id.navigation_profile)
        val menuItem = navView.menu.findItem(R.id.navigation_menu)
        val dashboardMenuItem = navView.menu.findItem(R.id.navigation_dashboard)

        profileMenuItem.setOnMenuItemClickListener {
            if (sharedPreferences.getString("token", "")!!.isNotEmpty()) {
                navController.navigate(R.id.navigation_profile)
            } else {
                navController.navigate(R.id.navigation_login)
            }
            true
        }

        menuItem.setOnMenuItemClickListener {
            navController.navigate(R.id.navigation_menu)
            true
        }

        dashboardMenuItem.setOnMenuItemClickListener {
            navController.navigate(R.id.navigation_dashboard)
            true
        }
    }
}