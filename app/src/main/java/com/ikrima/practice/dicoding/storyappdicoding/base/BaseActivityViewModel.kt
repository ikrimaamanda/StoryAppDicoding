package com.ikrima.practice.dicoding.storyappdicoding.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.ikrima.practice.dicoding.storyappdicoding.data.retrofit.ApiConfig
import com.ikrima.practice.dicoding.storyappdicoding.data.retrofit.StoryAppApiServices
import com.ikrima.practice.dicoding.storyappdicoding.utilities.sharedpreference.PreferencesHelper

abstract class BaseActivityViewModel<ActivityViewModel : ViewModel> : AppCompatActivity() {

    protected lateinit var viewModel: ActivityViewModel
    protected var setViewModel: ActivityViewModel ? = null
    protected lateinit var service : StoryAppApiServices
    protected lateinit var sharedPref : PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = setViewModel!!
        service = ApiConfig.getApiClientStoryApp(this)!!.create(StoryAppApiServices::class.java)
        sharedPref = PreferencesHelper(this)
    }

}