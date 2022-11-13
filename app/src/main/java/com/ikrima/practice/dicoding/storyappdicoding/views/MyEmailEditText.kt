package com.ikrima.practice.dicoding.storyappdicoding.views

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import androidx.appcompat.widget.AppCompatEditText
import com.ikrima.practice.dicoding.storyappdicoding.R

class MyEmailEditText : AppCompatEditText {

    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }


    private fun init() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                showWarning(p0)
            }

            override fun afterTextChanged(p0: Editable?) {
                // do nothing
            }

        })

    }

    private fun showWarning(p0: CharSequence?) {
        if (p0 != null) {
            error = if (p0.isEmpty()) {
                resources.getString(R.string.required)
            } else if (!Patterns.EMAIL_ADDRESS.matcher(p0).matches()) {
                resources.getString(R.string.invalid_email)
            } else {
                null
            }
        }
    }

}