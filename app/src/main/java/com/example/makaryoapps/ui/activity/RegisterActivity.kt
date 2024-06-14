package com.example.makaryoapps.ui.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import com.example.makaryoapps.R
import com.example.makaryoapps.databinding.ActivityLoginBinding
import com.example.makaryoapps.databinding.ActivityRegisterBinding
import com.example.makaryoapps.ui.fragment.HomeFragment

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {

            dialogSukses()
        }

        binding.tvLogin.setOnClickListener {
            val i = Intent(this@RegisterActivity, ActivityLogin::class.java)
            startActivity(i)
        }
    }

    //dialog
    private fun dialogSukses(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_succes_register )

        val next = dialog.findViewById<Button>(R.id.btn_suksesRegister)
        next.setOnClickListener {
            val i = Intent(this@RegisterActivity, ActivityLogin::class.java)
            startActivity(i)
        }

        dialog.show()
    }
}