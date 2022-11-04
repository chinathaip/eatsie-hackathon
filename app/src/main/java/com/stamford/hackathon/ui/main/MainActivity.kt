package com.stamford.hackathon.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stamford.hackathon.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        viewModel.food.observe(this) {
            println(it)
        }
    }
}