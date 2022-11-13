package com.ikrima.practice.dicoding.storyappdicoding.ui.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import com.ikrima.practice.dicoding.storyappdicoding.R
import com.ikrima.practice.dicoding.storyappdicoding.databinding.ActivityRegistrationBinding
import com.ikrima.practice.dicoding.storyappdicoding.views.MyEditText
import com.ikrima.practice.dicoding.storyappdicoding.views.MyEmailEditText
import com.ikrima.practice.dicoding.storyappdicoding.views.MyPasswordEditText

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClickListener()

    }

    private fun onClickListener() {
        binding.apply {

            btnRegistration.setOnClickListener {
                progressBarRegistration.visibility = View.VISIBLE
                validateUserRegistration()
            }
        }
    }

    private fun validateUserRegistration() {
        binding.apply {

            val name = edRegisterName.text.toString().trim()
            val emailText = edRegisterEmail.text.toString().trim()
            val passwordText = edRegisterPassword.text.toString().trim()

            validName(name, edRegisterName, progressBarRegistration)

            validEmail(emailText, edRegisterEmail, progressBarRegistration)

            validPassword(passwordText, edRegisterPassword, progressBarRegistration)

        }
    }

    private fun validName(
        name: String,
        edRegisterName: MyEditText,
        progressBarRegistration: ProgressBar
    ) {
        if (name.isEmpty()) {
            progressBarRegistration.visibility = View.GONE
            edRegisterName.error = resources.getString(R.string.required)
            edRegisterName.requestFocus()
            return
        } else {
            edRegisterName.error = null
        }
    }

    private fun validEmail(
        emailText: String,
        edRegisterEmail: MyEmailEditText,
        progressBarRegistration: ProgressBar,
    ) {
        if (emailText.isEmpty()) {
            progressBarRegistration.visibility = View.GONE
            edRegisterEmail.error = resources.getString(R.string.required)
            edRegisterEmail.requestFocus()
            return
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            progressBarRegistration.visibility = View.GONE
            edRegisterEmail.error = resources.getString(R.string.invalid_email)
            edRegisterEmail.requestFocus()
            return
        } else {
            edRegisterEmail.error = null
        }
    }


    private fun validPassword(
        passwordText: String,
        edRegisterPassword: MyPasswordEditText,
        progressBarRegistration: ProgressBar,
    ) {
        if (passwordText.isEmpty()) {
            progressBarRegistration.visibility = View.GONE
            edRegisterPassword.error = resources.getString(R.string.required)
            edRegisterPassword.requestFocus()
            return

        } else if (passwordText.length < 6) {
            progressBarRegistration.visibility = View.GONE
            edRegisterPassword.error = resources.getString(R.string.password_less_than_6)
            edRegisterPassword.requestFocus()
            return
        } else {
            edRegisterPassword.error = null
        }
    }


}