package com.example.waterdemandapp.ui.admin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.waterdemandapp.databinding.ActivityAdminMainBinding
import com.example.waterdemandapp.model.repositories.AuthRepository
import com.example.waterdemandapp.ui.admin.allorders.AllOrdersActivity
import com.example.waterdemandapp.ui.auth.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AdminMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminMainBinding

    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listener for "All Users" button
        binding.allOrders.setOnClickListener {
            val intent = Intent(this, AllOrdersActivity::class.java)
            startActivity(intent)
        }

        // Set click listener for "Logout" button
        binding.adminLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        authRepository.logout()

         val intent = Intent(this, LoginActivity::class.java)
         startActivity(intent)
         finish()
    }
}
