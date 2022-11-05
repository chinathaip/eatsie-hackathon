package com.stamford.hackathon.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stamford.hackathon.data.model.Item
import com.stamford.hackathon.domain.GetFoodUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val getFoodUseCase: GetFoodUseCase) : ViewModel() {

    private val _itemListing = MutableLiveData<List<Item>>()
    val itemListing: LiveData<List<Item>> = _itemListing


    private val _retrievedDataFailedEvent = MutableLiveData<String>()
    val retrievedDataFailedEvent: LiveData<String> = _retrievedDataFailedEvent

    init {
        getListing()
    }

    private fun getListing() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    getFoodUseCase.invoke()
                }
                _itemListing.value = response.getOrNull()?.items
            } catch (exception: Exception) {
                _retrievedDataFailedEvent.value = exception.message
            }
        }
    }
}