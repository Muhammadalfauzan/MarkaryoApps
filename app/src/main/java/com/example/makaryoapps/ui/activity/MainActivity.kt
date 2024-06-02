package com.example.makaryoapps.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        R.id.electronicFragment

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        setupBottomNavVisibility(navController)

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
}

