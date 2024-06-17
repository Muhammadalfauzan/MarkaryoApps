package com.example.makaryoapps.ui.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Window
import android.widget.Button
import android.widget.EditText
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.ActivityRegisterBinding
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var edEmail: EditText
    private lateinit var edPassword: EditText
    private lateinit var edConfirm: EditText
    private lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        edEmail = binding.emailLogLay
        edPassword = binding.etPassword
        edConfirm = binding.etConfirmPassword
        buttonRegister = binding.btnRegister

        edEmail.addTextChangedListener(textWatcher)
        edPassword.addTextChangedListener(textWatcher)
        edConfirm.addTextChangedListener(textWatcher)

        buttonRegister.isEnabled = false

        buttonRegister.setOnClickListener {
            dialogSukses()
        }

        binding.tvLogin.setOnClickListener {
            val i = Intent(this@RegisterActivity, ActivityLogin::class.java)
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

    private fun setEnableButton() {
        val email = edEmail.text.toString()
        val password = edPassword.text.toString()
        val confirmPassword = edConfirm.text.toString()

        buttonRegister.isEnabled = isEmailValid(email) &&
                password.isNotEmpty() &&
                password.length >= 8 &&
                confirmPassword.isNotEmpty() &&
                password == confirmPassword
    }

    private fun isEmailValid(email: String): Boolean {
        val emailPattern = Pattern.compile(
            "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        )
        return emailPattern.matcher(email).matches()
    }

    // Dialog
    private fun dialogSukses() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_succes_register)

        val next = dialog.findViewById<Button>(R.id.btn_suksesRegister)
        next.setOnClickListener {
            val i = Intent(this@RegisterActivity, ActivityLogin::class.java)
            startActivity(i)
            finish()
        }

        dialog.show()
    }
}
