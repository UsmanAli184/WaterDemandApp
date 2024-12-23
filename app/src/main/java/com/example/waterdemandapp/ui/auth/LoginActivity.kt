package com.example.waterdemandapp.ui.auth

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.waterdemandapp.ui.main.MainActivity
import com.example.waterdemandapp.ui.admin.AdminMainActivity
import com.example.waterdemandapp.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    lateinit var progressDialog: ProgressDialog
    lateinit var binding: ActivityLoginBinding
    val viewModel: LoginSignupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait while we check your credentials...")
        progressDialog.setCancelable(false)

        lifecycleScope.launch {
            viewModel.currentUser.collect { user ->
                if (user != null) {
                    progressDialog.dismiss()
                    if (user.email == "usmanaliadmin3833184@gmail.com") {
                        startActivity(Intent(this@LoginActivity, AdminMainActivity::class.java))
                    } else {
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    }
                    finish()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.failureMessage.collect { message ->
                if (message != null) {
                    progressDialog.dismiss()
                    Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.signupTxt.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
        binding.forgetPassword.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }

        binding.loginbtn.setOnClickListener {
            val email = binding.email.editText?.text.toString()
            val password = binding.password.editText?.text.toString()

            progressDialog.show()
            viewModel.login(email, password)
        }
    }
}
