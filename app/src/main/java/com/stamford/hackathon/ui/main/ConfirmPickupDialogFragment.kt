package com.stamford.hackathon.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.stamford.hackathon.R
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.databinding.BottomSheetDialogSelectItemBinding
import com.stamford.hackathon.ui.main.MainFragment.Companion.REQUEST_KEY
import com.stamford.hackathon.ui.main.MainFragment.Companion.RESULT_BUNDLE_KEY

class ConfirmPickupDialogFragment(private val data: ItemListingUiModel.ItemUiModel) :
    BottomSheetDialogFragment() {

    private lateinit var binding : BottomSheetDialogSelectItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetDialogSelectItemBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            confirmItemSelectionButton.setOnClickListener { showConfirmationDialog(data.itemId) }
            titleTextView.text = data.title
            itemDescriptionTextView.text = data.description
            itemPriceTextView.text = data.price
            itemWeightTextView.text = data.weight
            expDate.text = data.expiredTime
            mfgDate.text = data.boughtTime
            confirmItemImageView.load(data.imageUrl) {
                error(R.drawable.placeholder_image)
            }
        }
    }

    private fun showConfirmationDialog(itemId: String) {
        AlertDialog.Builder(context)
            .setMessage(getString(R.string.alert_dialog_title))
            .setPositiveButton(R.string.alert_dialog_yes) { _, _ ->
                requireActivity().supportFragmentManager.setFragmentResult(
                    REQUEST_KEY,
                    bundleOf(RESULT_BUNDLE_KEY to itemId)
                )
                dismiss()
            }
            .setNegativeButton(R.string.alert_dialog_no) { _, _ -> dismiss() }
            .create()
            .show()
    }

}