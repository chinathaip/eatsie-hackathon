package com.stamford.hackathon.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.stamford.hackathon.R
import com.stamford.hackathon.core.model.ui.UserUiModel
import com.stamford.hackathon.databinding.ActivityMainBinding
import com.stamford.hackathon.extension.whenNotBlank
import com.stamford.hackathon.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra-user"

        fun createIntent(context: Context, user: UserUiModel): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(EXTRA_USER, user)
            }
        }
    }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val user by lazy { intent.extras?.getParcelable(EXTRA_USER) as? UserUiModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            setFragment(MainFragment())
        }
        setupView()
    }

    private fun setupView() {
        user?.username?.whenNotBlank { userName ->
            binding.toolbar.greetingTextView.text = getString(R.string.welcome, userName)
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mainPage -> setFragment(MainFragment())
                R.id.searchPage -> Unit // searchFragment
                R.id.profilePage -> setFragment(ProfileFragment().apply {
                    arguments = intent.extras
                })
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