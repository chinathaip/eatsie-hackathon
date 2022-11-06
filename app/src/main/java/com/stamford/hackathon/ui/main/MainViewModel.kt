package com.stamford.hackathon.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.domain.GetFoodUseCase
import com.stamford.hackathon.ui.main.mapper.ItemToItemListingUiModelMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val getFoodUseCase: GetFoodUseCase) : ViewModel() {

    private val _itemListing = MutableLiveData<List<ItemListingUiModel>>()
    val itemListing: LiveData<List<ItemListingUiModel>> = _itemListing


    private val _retrievedDataFailedEvent = MutableLiveData<String>()
    val retrievedDataFailedEvent: LiveData<String> = _retrievedDataFailedEvent

    init {
        getListing()
    }

    private fun getListing() {
        viewModelScope.launch {
            try {
                val newItemListings = mutableListOf<ItemListingUiModel>()
                val response = withContext(Dispatchers.IO) {
                    getFoodUseCase.invoke()
                        .onSuccess { it?.items }
                        .onFailure { throw it }
                }
                newItemListings.add(ItemListingUiModel.GroupHeader("Items from nearby restaurants"))
                newItemListings.addAll(response.getOrNull()?.items?.map {
                    ItemToItemListingUiModelMapper.map(it)
                } ?: emptyList())

                _itemListing.value = newItemListings
            } catch (exception: Exception) {
                _retrievedDataFailedEvent.value = exception.message
            }
        }
    }
}