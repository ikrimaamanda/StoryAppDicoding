package com.ikrima.practice.dicoding.storyappdicoding.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import com.ikrima.practice.dicoding.storyappdicoding.R
import com.ikrima.practice.dicoding.storyappdicoding.databinding.ActivityLoginBinding
import com.ikrima.practice.dicoding.storyappdicoding.ui.registration.RegistrationActivity
import com.ikrima.practice.dicoding.storyappdicoding.views.MyEmailEditText
import com.ikrima.practice.dicoding.storyappdicoding.views.MyPasswordEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClickListener()

    }

    private fun onClickListener() {
        binding.apply {

            tvNewRegistration.setOnClickListener {
                val i = Intent(this@LoginActivity, RegistrationActivity::class.java)
                startActivity(i)
                finish()
            }

            btnLogin.setOnClickListener {
                progressBarLogin.visibility = View.VISIBLE
                validateUserLogin()
            }

        }
    }


    private fun validateUserLogin() {
        binding.apply {

            val emailText = edLoginEmail.text.toString().trim()
            val passwordText = edLoginPassword.text.toString().trim()

            validEmail(emailText,edLoginEmail, progressBarLogin)

            validPassword(passwordText, edLoginPassword, progressBarLogin)

        }
    }


    private fun validEmail(
        emailText: String,
        edLoginEmail: MyEmailEditText,
        progressBarLogin: ProgressBar
    ) {
        if (emailText.isEmpty()) {
            progressBarLogin.visibility = View.GONE
            edLoginEmail.error = resources.getString(R.string.required)
            edLoginEmail.requestFocus()
            return
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            progressBarLogin.visibility = View.GONE
            edLoginEmail.error = resources.getString(R.string.invalid_email)
            edLoginEmail.requestFocus()
            return
        } else {
            edLoginEmail.error = null
        }
    }


    private fun validPassword(
        passwordText: String,
        edLoginPassword: MyPasswordEditText,
        progressBarLogin: ProgressBar
    ) {
        if (passwordText.isEmpty()) {
            progressBarLogin.visibility = View.GONE
            edLoginPassword.error = resources.getString(R.string.required)
            edLoginPassword.requestFocus()
            return

        } else if (passwordText.length < 6) {
            progressBarLogin.visibility = View.GONE
            edLoginPassword.error = resources.getString(R.string.password_less_than_6)
            edLoginPassword.requestFocus()
            return
        } else {
            edLoginPassword.error = null
        }
    }

}