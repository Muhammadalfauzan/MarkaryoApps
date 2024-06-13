package com.example.makaryoapps.ui.activity

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.Toast
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.ActivityLoginBinding

class ActivityLogin : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val dummyEmail = "fauzan2024@gmail.com"
    private val dummyPassword = "12345678"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (isLoggedIn()) {
            redirectToMain()
            return
        }
        sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            val email = binding.emailLogLay.text.toString()
            val password = binding.etPassword.text.toString()
            if (validateLogin(email, password)) {
                showProgressBar()
                Handler(Looper.getMainLooper()).postDelayed({
                    saveLoginStatus(true)
                    hideProgressBar()
                    dialogSukses()
                }, 2000)
            } else {
                Toast.makeText(this, "Email or Password is incorrect", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvRegister.setOnClickListener {
            val i = Intent(this@ActivityLogin, RegisterActivity::class.java)
            startActivity(i)
        }
    }

    private fun validateLogin(email: String, password: String): Boolean {
        return email == dummyEmail && password == dummyPassword
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnLogin.isEnabled = false
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.btnLogin.isEnabled = true
    }

    private fun saveLoginStatus(isLoggedIn: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", isLoggedIn)
        editor.apply()
    }

    private fun dialogSukses() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_succes)

        val next = dialog.findViewById<Button>(R.id.btn_sukses)
        next.setOnClickListener {
            val i = Intent(this@ActivityLogin, MainActivity::class.java)
            startActivity(i)
            finish()
        }

        dialog.show()
    }
    private fun isLoggedIn(): Boolean {
        val sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    private fun redirectToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
