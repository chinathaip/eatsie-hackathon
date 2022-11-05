package com.stamford.hackathon.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import com.stamford.hackathon.databinding.FragmentMainBinding
import com.stamford.hackathon.ui.main.adapter.ItemListingAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: FragmentMainBinding
    private val itemListingAdapter = ItemListingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeViewModel()
    }

    private fun setupView() {
        binding.mainMenuRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = itemListingAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.itemListing.observe(viewLifecycleOwner) {
            itemListingAdapter.submitList(it)
        }
        viewModel.retrievedDataFailedEvent.observe(viewLifecycleOwner) {
            Log.d("LOL", it)
        }
    }
}