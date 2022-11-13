package com.ikrima.practice.dicoding.storyappdicoding.ui.contents.maincontent.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ikrima.practice.dicoding.storyappdicoding.R
import com.ikrima.practice.dicoding.storyappdicoding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}