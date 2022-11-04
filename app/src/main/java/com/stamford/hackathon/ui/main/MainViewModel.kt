package com.stamford.hackathon.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stamford.hackathon.data.model.Coins
import com.stamford.hackathon.domain.GetFoodUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val getFoodUseCase: GetFoodUseCase) : ViewModel() {

    val food = MutableLiveData<Coins>()

    init {
        getFood()
    }

    fun getFood() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                getFoodUseCase.invoke()
            }
            food.value = response
        }
    }
}