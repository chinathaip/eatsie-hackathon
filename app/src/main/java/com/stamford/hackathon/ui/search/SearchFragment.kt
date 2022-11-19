package com.stamford.hackathon.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.stamford.hackathon.core.OnItemListingClick
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.databinding.FragmentSearchBinding
import com.stamford.hackathon.ui.main.MainViewModel
import com.stamford.hackathon.ui.search.adapter.SearchResultAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val mainViewModel by viewModel<MainViewModel>()
    private val searchResultAdapter = SearchResultAdapter(OnSearchItemClickListener())

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(LayoutInflater.from(parent?.context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.itemListingSearchView.setOnQueryTextListener(OnSearchTypeListener())
        binding.searchResultRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchResultAdapter
        }
    }

    inner class OnSearchTypeListener : SearchView.OnQueryTextListener {
        private fun search(query: String?) {
            lifecycleScope.launch {
                query?.takeIf { it.isNotBlank() }?.let { query ->
                    mainViewModel.beginSearch(query.lowercase())
                        .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                        .onEach { searchResultAdapter.submitList(it) }.collect()
                } ?: searchResultAdapter.submitList(emptyList())
            }
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            search(query)
            return true
        }

        override fun onQueryTextChange(query: String?): Boolean {
            search(query)
            return true
        }
    }

    inner class OnSearchItemClickListener : OnItemListingClick {
        override fun onItemClick(data: ItemListingUiModel.ItemUiModel?) = Unit

    }
}