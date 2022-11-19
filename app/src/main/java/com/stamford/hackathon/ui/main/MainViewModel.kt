package com.stamford.hackathon.ui.main

import androidx.lifecycle.*
import com.stamford.hackathon.core.Const
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.domain.ClientPickupConfirmUseCase
import com.stamford.hackathon.domain.GetListingUseCase
import com.stamford.hackathon.domain.GetSortedListingUseCase
import com.stamford.hackathon.ui.main.mapper.ItemToItemListingUiModelMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val THREE_SECONDS = 3000L

class MainViewModel(
    private val getListingUseCase: GetListingUseCase,
    private val getSortedListingUseCase: GetSortedListingUseCase,
    private val confirmPickupUseCase: ClientPickupConfirmUseCase
) : ViewModel() {

    private val _itemListing = MutableLiveData<List<ItemListingUiModel>>()
    val itemListing: LiveData<List<ItemListingUiModel>> = _itemListing

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

    fun beginSearch(query: String): Flow<List<ItemListingUiModel.ItemUiModel>> {
        val searchItems = emptyList<ItemListingUiModel.ItemUiModel>().toMutableList()
        return flow {
            _itemListing.value.orEmpty().filterIsInstance<ItemListingUiModel.ItemUiModel>()
                .forEach { item ->
                    if (item.title.lowercase().contains(query)) {
                        searchItems.add(item)
                        emit(searchItems)
                    }
                }
        }
    }

    fun getSortedListing(type: String) {
        viewModelScope.launch {
            try {
                prepareItemListing()
                delay(THREE_SECONDS)
                val newItemListings = _itemListing.value.orEmpty().toMutableList().apply {
                    remove(ItemListingUiModel.Loading)
                }
                val response = withContext(Dispatchers.IO) {
                    getSortedListingUseCase(type)
                        .onSuccess { it?.items }
                        .onFailure { throw it }
                }
                newItemListings.addAll(response.getOrNull()?.items?.mapNotNull {
                    ItemToItemListingUiModelMapper.map(it, Const.STATUS_AVAILABLE)
                } ?: emptyList())
                _itemListing.value = newItemListings
            } catch (exception: Exception) {
                _retrievedDataFailedEvent.value = exception.message
            }
        }
    }

    fun getListing() {
        viewModelScope.launch {
            try {
                prepareItemListing()
                delay(THREE_SECONDS)
                val newItemListings = _itemListing.value.orEmpty().toMutableList().apply {
                    remove(ItemListingUiModel.Loading)
                }
                val response = withContext(Dispatchers.IO) {
                    getListingUseCase.invoke()
                        .onSuccess { it?.items }
                        .onFailure { throw it }
                }
                newItemListings.addAll(response.getOrNull()?.items?.mapNotNull {
                    ItemToItemListingUiModelMapper.map(it, Const.STATUS_AVAILABLE)
                } ?: emptyList())

                _itemListing.value = newItemListings
            } catch (exception: Exception) {
                _retrievedDataFailedEvent.value = exception.message
            }
        }
    }

    private fun prepareItemListing() {
        _itemListing.value =
            listOf(
                ItemListingUiModel.GroupHeader("Pick your category"),
                ItemListingUiModel.createCategories(),
                ItemListingUiModel.GroupHeader("Items from nearby restaurants"),
                ItemListingUiModel.Loading
            )
    }

    private fun buildRequestBody(key: String, value: String): Map<String, String> {
        return mapOf(key to value)
    }
}