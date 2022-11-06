package com.stamford.hackathon.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.stamford.hackathon.R
import com.stamford.hackathon.databinding.ActivityMainBinding
import com.stamford.hackathon.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) { setFragment(MainFragment()) }

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mainPage -> setFragment(MainFragment())
                R.id.searchPage -> Unit // searchFragment
                R.id.profilePage -> setFragment(ProfileFragment())
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(binding.fragmentContainerView.id, fragment)
        }
    }

}