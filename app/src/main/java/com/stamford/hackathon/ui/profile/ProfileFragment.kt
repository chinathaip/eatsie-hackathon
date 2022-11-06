package com.stamford.hackathon.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.stamford.hackathon.databinding.FragmentProfileBinding
import com.stamford.hackathon.ui.profile.adapter.PendingOrderAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModel<ProfileViewModel>()
    private val pendingOrderAdapter = PendingOrderAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentProfileBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeViewModel()
    }

    private fun setupView() {
        binding.pendingOrderRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pendingOrderAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.pendingOrders.observe(viewLifecycleOwner) {
            pendingOrderAdapter.submitList(it)
            Log.d("LOL", it.toString())
        }
        viewModel.retrievedDataFailedEvent.observe(viewLifecycleOwner) {
            Log.d("LOL", "error: $it")
        }
    }

}