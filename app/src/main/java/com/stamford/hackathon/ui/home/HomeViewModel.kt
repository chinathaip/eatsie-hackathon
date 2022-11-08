package com.stamford.hackathon.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stamford.hackathon.core.Const
import com.stamford.hackathon.domain.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loginSuccess = MutableLiveData<Unit>()
    val loginSuccess: LiveData<Unit> = _loginSuccess

    private val _loginFailed = MutableLiveData<String>()
    val loginFailed: LiveData<String> = _loginFailed

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun handleLogin(userName: String, password: String) {
        if (userName.isNotBlank() && password.isNotBlank()) {
            login(userName, password)
        } else {
            _loginFailed.value = "please fill all the information"
        }
    }

    private fun login(userName: String, password: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                withContext(Dispatchers.IO) {
                    loginUseCase(createLoginBody(userName, password))
                        .onFailure { throw it }
                }
                _loginSuccess.value = Unit
            } catch (exception: Exception) {
                _loginFailed.value = "invalid login credentials"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun createLoginBody(userName: String, password: String): Map<String, String> {
        return mapOf(
            Const.LOGIN_USERNAME to userName,
            Const.LOGIN_PASSWORD to password
        )
    }
}