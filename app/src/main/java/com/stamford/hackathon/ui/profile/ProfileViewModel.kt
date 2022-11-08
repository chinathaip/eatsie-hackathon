package com.stamford.hackathon.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stamford.hackathon.core.Const
import com.stamford.hackathon.core.model.ui.ItemListingUiModel
import com.stamford.hackathon.domain.GetListingByStatusUseCase
import com.stamford.hackathon.ui.main.mapper.ItemToItemListingUiModelMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(private val getListingByStatusUseCase: GetListingByStatusUseCase) :
    ViewModel() {

    private val _pendingOrders = MutableLiveData<List<ItemListingUiModel.ItemUiModel>>()
    val pendingOrders: LiveData<List<ItemListingUiModel.ItemUiModel>> = _pendingOrders

    private val _retrievedDataFailedEvent = MutableLiveData<String>()
    val retrievedDataFailedEvent: LiveData<String> = _retrievedDataFailedEvent

    init {
        getRestaurantPendingOrders()
    }

    private fun getRestaurantPendingOrders() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    getListingByStatusUseCase.invoke(Const.STATUS_RESTAURANT_PENDING)
                        .onSuccess { it?.items }
                        .onFailure { throw it }
                }
                _pendingOrders.value =
                    response.getOrNull()?.items?.mapNotNull {
                        ItemToItemListingUiModelMapper.map(
                            it,
                            Const.STATUS_RESTAURANT_PENDING
                        )
                    } ?: emptyList()
            } catch (exception: Exception) {
                _retrievedDataFailedEvent.value = exception.toString()
            }
        }
    }
}