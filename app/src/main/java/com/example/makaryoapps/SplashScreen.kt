package com.example.makaryoapps

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.makaryoapps.ui.activity.ActivityLogin


@SuppressLint("CustomSplashScreen")
@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    private val splashTimeOut: Long = 4500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            // Pindah ke activity login setelah SPLASH_TIME_OUT
            val intent = Intent(this@SplashScreen, ActivityLogin::class.java)
            startActivity(intent)
            finish()
        }, splashTimeOut)
    }

}
