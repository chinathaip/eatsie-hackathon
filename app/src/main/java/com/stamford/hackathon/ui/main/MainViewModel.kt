package com.stamford.hackathon.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stamford.hackathon.core.Const
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.domain.ClientPickupConfirmUseCase
import com.stamford.hackathon.domain.GetListingUseCase
import com.stamford.hackathon.domain.GetSortedListingUseCase
import com.stamford.hackathon.ui.main.mapper.ItemToItemListingUiModelMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val getListingUseCase: GetListingUseCase,
    private val getSortedListingUseCase: GetSortedListingUseCase,
    private val confirmPickupUseCase: ClientPickupConfirmUseCase
) : ViewModel() {

    private val _itemListing = MutableLiveData<List<ItemListingUiModel>>()
    val itemListing: LiveData<List<ItemListingUiModel>> = _itemListing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _confirmPickupSuccessEvent = MutableLiveData<Unit>()
    val confirmPickUpSuccessEvent: LiveData<Unit> = _confirmPickupSuccessEvent

    private val _retrievedDataFailedEvent = MutableLiveData<String>()
    val retrievedDataFailedEvent: LiveData<String> = _retrievedDataFailedEvent

    init {
        getListing()
    }

    fun confirmPickup(itemId: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    confirmPickupUseCase(
                        ClientPickupConfirmUseCase.Param(
                            buildRequestBody(
                                "listingId",
                                itemId
                            )
                        )
                    )
                        .onSuccess { it.toString() }
                        .onFailure { throw it }
                }
                _confirmPickupSuccessEvent.value = Unit
            } catch (exception: Exception) {
                _retrievedDataFailedEvent.value = exception.message
            }
        }
    }

    fun getSortedListing(type: String) {
        viewModelScope.launch {
            try {
                _itemListing.value = emptyList()
                getCategory()
                val newItemListings = _itemListing.value.orEmpty().toMutableList()
                val response = withContext(Dispatchers.IO) {
                    getSortedListingUseCase(type)
                        .onSuccess { it?.items }
                        .onFailure { throw it }
                }
                newItemListings.add(ItemListingUiModel.GroupHeader("Items from nearby restaurants"))
                newItemListings.addAll(response.getOrNull()?.items?.mapNotNull {
                    ItemToItemListingUiModelMapper.map(it, Const.STATUS_AVAILABLE)
                } ?: emptyList())
                _itemListing.value = newItemListings
            } catch (exception: Exception) {
                _retrievedDataFailedEvent.value = exception.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getListing() {
        viewModelScope.launch {
            try {
                _itemListing.value = emptyList()
                getCategory()
                val newItemListings = _itemListing.value.orEmpty().toMutableList()
                val response = withContext(Dispatchers.IO) {
                    getListingUseCase.invoke()
                        .onSuccess { it?.items }
                        .onFailure { throw it }
                }
                newItemListings.add(ItemListingUiModel.GroupHeader("Items from nearby restaurants"))
                newItemListings.addAll(response.getOrNull()?.items?.mapNotNull {
                    ItemToItemListingUiModelMapper.map(it, Const.STATUS_AVAILABLE)
                } ?: emptyList())

                _itemListing.value = newItemListings
            } catch (exception: Exception) {
                _retrievedDataFailedEvent.value = exception.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun getCategory() {
        _isLoading.value = true
        val newItemListings = _itemListing.value.orEmpty().toMutableList()
        newItemListings.add(ItemListingUiModel.GroupHeader("Pick your category"))
        newItemListings.addAll(listOf(ItemListingUiModel.createCategories()))
        _itemListing.value = newItemListings
    }

    private fun buildRequestBody(key: String, value: String): Map<String, String> {
        return mapOf(key to value)
    }
}