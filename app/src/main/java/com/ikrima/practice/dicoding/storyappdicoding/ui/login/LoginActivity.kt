package com.ikrima.practice.dicoding.storyappdicoding.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ikrima.practice.dicoding.storyappdicoding.R
import com.ikrima.practice.dicoding.storyappdicoding.base.BaseActivityViewModel
import com.ikrima.practice.dicoding.storyappdicoding.data.responses.LoginUserResponses
import com.ikrima.practice.dicoding.storyappdicoding.databinding.ActivityLoginBinding
import com.ikrima.practice.dicoding.storyappdicoding.ui.contents.maincontent.activities.MainActivity
import com.ikrima.practice.dicoding.storyappdicoding.ui.registration.RegistrationActivity
import com.ikrima.practice.dicoding.storyappdicoding.utilities.helper.ResultWrapper
import com.ikrima.practice.dicoding.storyappdicoding.viewmodels.LoginViewModel
import com.ikrima.practice.dicoding.storyappdicoding.views.MyEmailEditText
import com.ikrima.practice.dicoding.storyappdicoding.views.MyPasswordEditText

class LoginActivity : BaseActivityViewModel<LoginViewModel>() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setViewModel =ViewModelProvider(this)[LoginViewModel::class.java]

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingViewModel()

        onClickListener()

        subscribeLoginUserLiveData()

    }

    private fun settingViewModel() {
        viewModel.apply {
            setService(service)
            setSharedPref(sharedPref)
        }
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

            cbShowPassword.setOnCheckedChangeListener { _, p1 ->
                if (!p1) {
                    edLoginPassword.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                } else {
                    edLoginPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                }
            }

        }
    }


    private fun validateUserLogin() {
        binding.apply {

            val emailText = edLoginEmail.text.toString().trim()
            val passwordText = edLoginPassword.text.toString().trim()

            validEmail(emailText,edLoginEmail, progressBarLogin)

            validPassword(passwordText, edLoginPassword, progressBarLogin)

            viewModel.loginUser(emailText, passwordText)

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


    private fun subscribeLoginUserLiveData() {
        binding.apply {
            viewModel.responseLoginUser.observe(this@LoginActivity) {
                when(it) {
                    is ResultWrapper.Default -> {
                    }
                    is ResultWrapper.Empty -> {
                        progressBarLogin.visibility = View.GONE

                        Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_SHORT).show()

                    }
                    is ResultWrapper.Failure -> {
                        progressBarLogin.visibility = View.GONE

                        Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_SHORT).show()

                    }
                    is ResultWrapper.Loading -> {
                        progressBarLogin.visibility = View.VISIBLE
                    }
                    is ResultWrapper.Success -> {
                        progressBarLogin.visibility = View.GONE

                        val data = it.data as LoginUserResponses
                        Toast.makeText(this@LoginActivity, data.message, Toast.LENGTH_SHORT).show()

                        val i = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(i)
                        finish()
                    }
                    else -> {
                        progressBarLogin.visibility = View.GONE

                        Toast.makeText(this@LoginActivity, "Server dalam perbaikan", Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }
    }

}