package com.stamford.hackathon.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.stamford.hackathon.databinding.FragmentHomeBinding
import com.stamford.hackathon.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeViewModel()
    }

    private fun setupView() {
        binding.loginButton.setOnClickListener {
            viewModel.handleLogin(
                binding.usernameInput.text.toString(),
                binding.passwordInput.text.toString()
            )
        }
    }

    private fun observeViewModel() {
        viewModel.loginSuccess.observe(viewLifecycleOwner) {
            startActivity(MainActivity.createIntent(requireContext()))
        }
        viewModel.loginFailed.observe(viewLifecycleOwner) { errorText ->
            Snackbar.make(binding.root, errorText, Snackbar.LENGTH_LONG).show()
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            binding.loadingProgressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }
    }
}