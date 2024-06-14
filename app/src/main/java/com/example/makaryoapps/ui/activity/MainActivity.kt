package com.example.makaryoapps.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val fragmentsToHideBottomNav = setOf(
        R.id.detailFragment,
        R.id.builderFragment,
        R.id.cleaningFragment,
        R.id.otomotifFragment,
        R.id.electronicFragment,
        R.id.detailChatFragment,
        R.id.confirmationFragment,
        R.id.adressListFragment,
        R.id.receiptFragment,
        R.id.addAddressFragment,
        R.id.updateAddressFragment,
        R.id.updateProfileFragment,
        R.id.notificationFragment,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isLoggedIn = isLoggedIn()
        if (!isLoggedIn) {
            // Redirect to login activity if not logged in
            redirectToLogin()
            return
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        setupBottomNavVisibility(navController)

        // Handle bottom navigation item clicks to ensure correct navigation
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.historyFragment -> {
                    navController.navigate(R.id.historyFragment)
                    true
                }
                R.id.chatFragment -> {
                    navController.navigate(R.id.chatFragment)
                    true
                }
                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }
                // Add other navigation items here
                else -> false
            }
        }
    }


    private fun setupBottomNavVisibility(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigationView.visibility =
                if (destination.id in fragmentsToHideBottomNav) {
                    BottomNavigationView.GONE
                } else {
                    BottomNavigationView.VISIBLE
                }
        }
    }
    private fun isLoggedIn(): Boolean {
        val sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    private fun redirectToLogin() {
        val intent = Intent(this, ActivityLogin::class.java)
        startActivity(intent)
        finish()
    }
}

