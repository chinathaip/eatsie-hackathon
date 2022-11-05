package com.stamford.hackathon.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import com.stamford.hackathon.databinding.FragmentMainBinding
import com.stamford.hackathon.ui.main.adapter.CategoryAdapter
import com.stamford.hackathon.ui.main.adapter.RestaurantAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: FragmentMainBinding
    private val categoryAdapter = CategoryAdapter()
    private val restaurantAdapter = RestaurantAdapter()
    private val mainAdapter = MergeAdapter(categoryAdapter, restaurantAdapter)

    override fun onCreateView(
        inflater: LayoutInflater, parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
        observeViewModel()
    }

    private fun setupView() {
        binding.mainMenuRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = mainAdapter
        }
    }

    private fun observeViewModel() {

    }

}