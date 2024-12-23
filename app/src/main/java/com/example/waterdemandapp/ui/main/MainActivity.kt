package com.example.waterdemandapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.waterdemandapp.R
import com.example.waterdemandapp.model.repositories.AuthRepository
import com.example.waterdemandapp.ui.auth.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var authRepository: AuthRepository  // Inject AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        // Set up NavController with Bottom Navigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setupWithNavController(navHostFragment.navController)

        // Drawer Layout
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        findViewById<ImageView>(R.id.drawer_icon).setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Handle item selection from the menu (logout in this case)
        val navigationView = findViewById<com.google.android.material.navigation.NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_logout -> {
                    logout() // Call the logout function when "Logout" item is clicked
                    true
                }
                else -> false
            }
        }
    }

    // Handle logout functionality
    private fun logout() {
        authRepository.logout()  // Call the logout function from AuthRepository
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()

        // Redirect to LoginActivity after logout
        startActivity(Intent(this, LoginActivity::class.java))
        finish()  // Close the MainActivity
    }
}
