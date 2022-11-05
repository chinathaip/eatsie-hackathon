package com.stamford.hackathon.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.databinding.FragmentMainBinding
import com.stamford.hackathon.ui.main.adapter.ItemListingAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        const val REQUEST_KEY = "request-get-confirm-result"
    }

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: FragmentMainBinding
    private val itemListingAdapter = ItemListingAdapter(OnItemClickListener())

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
        requireActivity().supportFragmentManager.setFragmentResultListener(
            REQUEST_KEY, viewLifecycleOwner
        ) { _, bundle ->
            //to be replaced with calling API
            val result = bundle.getString("test")
            Log.d("LOL", "dialog fragment result = $result")
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

    inner class OnItemClickListener : OnItemListingClick {
        override fun onItemClick(data: ItemListingUiModel.ItemUiModel?) {
            data?.let {
                ConfirmPickupDialogFragment(it).show(childFragmentManager, "")
            }
        }
    }
}