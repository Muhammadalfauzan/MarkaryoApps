package com.example.makaryoapps.ui.activity

import android.app.Dialog
import android.content.Intent
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
import com.example.makaryoapps.databinding.ActivityMainBinding

class ActivityLogin : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
 /*   private val dummyEmail = "fauzan2024@gmail.com"
    private val dummyPassword = "12345678"*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            hideProgressBar()
            dialogSukses()
           /* val email = binding.emailLogLay.text.toString()
            val password = binding.etPassword.text.toString()
            if (validateLogin(email, password)) {
                showProgressBar()
                // Simulate login delay
                Handler(Looper.getMainLooper()).postDelayed({

                }, 2000) // 2 seconds delay
            } else {
                Toast.makeText(this, "Email or Password is incorrect", Toast.LENGTH_SHORT).show()
            }*/
        }

        binding.tvRegister.setOnClickListener {
            val i = Intent(this@ActivityLogin, RegisterActivity::class.java)
            startActivity(i)
        }
    }
 /*   private fun validateLogin(email: String, password: String): Boolean {
        return email == dummyEmail && password == dummyPassword
    }*/
    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnLogin.isEnabled = false
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.btnLogin.isEnabled = true
    }
    //dialog
    private fun dialogSukses(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_succes)

        val next = dialog.findViewById<Button>(R.id.btn_sukses)
        next.setOnClickListener {
            val i = Intent(this@ActivityLogin, MainActivity::class.java)
            startActivity(i)
        }

        dialog.show()
    }
}