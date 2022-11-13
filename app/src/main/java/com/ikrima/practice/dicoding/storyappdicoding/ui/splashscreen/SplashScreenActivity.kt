package com.ikrima.practice.dicoding.storyappdicoding.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.ikrima.practice.dicoding.storyappdicoding.databinding.ActivitySplashScreenBinding
import com.ikrima.practice.dicoding.storyappdicoding.ui.contents.maincontent.activities.MainActivity
import com.ikrima.practice.dicoding.storyappdicoding.ui.login.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handler().postDelayed({
        Handler(Looper.getMainLooper()).postDelayed({
//            val intent = Intent(this, MainActivity::class.java)
            val intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.
    }
}