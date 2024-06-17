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
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.ActivityLoginBinding
import java.util.regex.Pattern

class ActivityLogin : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val dummyEmail = "ticha2024@gmail.com"
    private val dummyPassword = "12345678"
    private lateinit var edEmail: EditText
    private lateinit var edPassword: EditText

    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

        if (isLoggedIn()) {
            redirectToMain()
            return
        }

        edEmail = binding.emailLogLay
        edPassword = binding.etPassword
        buttonLogin = binding.btnLogin


        edEmail.addTextChangedListener(textWatcher)
        edPassword.addTextChangedListener(textWatcher)

        buttonLogin.isEnabled = false

        binding.etPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                attemptLogin()
                true
            } else {
                false
            }
        }

        buttonLogin.setOnClickListener {
            attemptLogin()
        }

        binding.tvRegister.setOnClickListener {
            val i = Intent(this@ActivityLogin, RegisterActivity::class.java)
            startActivity(i)
        }
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            setEnableButton()
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private fun attemptLogin() {
        val email = edEmail.text.toString()
        val password = edPassword.text.toString()
        if (validateLogin(email, password)) {
            showProgressBar()
            Handler(Looper.getMainLooper()).postDelayed({
                saveLoginStatus(true)
                hideProgressBar()
                dialogSukses()
            }, 2000)
        } else {
            showProgressBar()
            Handler(Looper.getMainLooper()).postDelayed({
                hideProgressBar()
                dialogFailed()
            }, 2000)

        }
    }

    private fun validateLogin(email: String, password: String): Boolean {
        return email == dummyEmail && password == dummyPassword
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
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

    private fun dialogFailed() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_failed_login)

        val next = dialog.findViewById<Button>(R.id.btn_kembali)
        next.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun setEnableButton() {
        buttonLogin.isEnabled = isEmailValid(edEmail.text.toString()) &&
                edPassword.text.isNotEmpty() &&
                edPassword.text.length >= 8
    }

    private fun isEmailValid(email: String): Boolean {
        val emailPattern = Pattern.compile(
            "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        )
        return emailPattern.matcher(email).matches()
    }

    private fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    private fun redirectToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
