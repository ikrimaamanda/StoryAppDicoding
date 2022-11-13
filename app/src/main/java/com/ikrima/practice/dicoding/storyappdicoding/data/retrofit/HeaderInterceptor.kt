package com.ikrima.practice.dicoding.storyappdicoding.data.retrofit

import android.content.Context
import com.ikrima.practice.dicoding.storyappdicoding.utilities.sharedpreference.Constant
import com.ikrima.practice.dicoding.storyappdicoding.utilities.sharedpreference.PreferencesHelper
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val context : Context) : Interceptor {

    private lateinit var sharedPref : PreferencesHelper

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        sharedPref = PreferencesHelper(context)

        val tokenAuth = sharedPref.getValueString(Constant.prefUserToken)
        proceed(
            request().newBuilder()
                .addHeader("Authorization", "Bearer $tokenAuth")
                .header("Connection", "close")
                .removeHeader("Content-Length")
                .build()
        )
    }
}