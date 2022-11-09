package com.stamford.hackathon.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.google.android.material.snackbar.Snackbar
import com.stamford.hackathon.R
import com.stamford.hackathon.core.OnCategoryClick
import com.stamford.hackathon.core.OnItemListingClick
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.databinding.FragmentMainBinding
import com.stamford.hackathon.ui.main.adapter.ItemListingAdapter
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        const val REQUEST_KEY = "request-get-confirm-result"
        const val RESULT_BUNDLE_KEY = "bundle-itemId"
        const val LOADING_VIEW = 0
        const val CONTENT_VIEW = 1
    }

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: FragmentMainBinding
    private val itemListingAdapter =
        ItemListingAdapter(OnItemClickListener(), OnCategoryClickListener())

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
            bundle.getString(RESULT_BUNDLE_KEY)?.let { itemId ->
                viewModel.confirmPickup(itemId)
            }
        }
        binding.highlightImageView.load(
            "https://media.discordapp.net/attachments/1028047367909425333/1038740748746621010/unknown.png?width=647&height=426"
        )
    }

    private fun observeViewModel() {
        viewModel.itemListing.observe(viewLifecycleOwner) {
            itemListingAdapter.submitList(it)
        }
        viewModel.confirmPickUpSuccessEvent.observe(viewLifecycleOwner) {
            Snackbar.make(
                binding.root,
                "Done! Now please wait for the restaurant to acknowledge!",
                Snackbar.LENGTH_LONG
            ).show()
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

    inner class OnCategoryClickListener : OnCategoryClick {
        override fun onCategoryClick(categoryType: String?) {
            if (categoryType?.isNotBlank() == true) {
                viewModel.getSortedListing(categoryType)
            } else {
                viewModel.getListing()
            }
        }
    }
}