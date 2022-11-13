package com.ikrima.practice.dicoding.storyappdicoding.views

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.ikrima.practice.dicoding.storyappdicoding.R

class MyPasswordEditText : AppCompatEditText {

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
                if (p0.toString().length < 6) showWarning() else hideWarning()
            }

            override fun afterTextChanged(p0: Editable?) {
                // do nothing
            }

        })

    }

    private fun showWarning() {
        error = resources.getString(R.string.password_less_than_6)
    }

    private fun hideWarning() {
        error = null
    }

}