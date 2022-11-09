package com.stamford.hackathon.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stamford.hackathon.core.Const
import com.stamford.hackathon.core.model.ui.UserUiModel
import com.stamford.hackathon.domain.LoginUseCase
import com.stamford.hackathon.ui.home.mapper.UserToUserUiModelMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loginSuccess = MutableLiveData<UserUiModel>()
    val loginSuccess: LiveData<UserUiModel> = _loginSuccess

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
                val response = withContext(Dispatchers.IO) {
                    loginUseCase(LoginUseCase.Param(createLoginBody(userName, password)))
                        .onSuccess { it?.userinfo }
                        .onFailure { throw it }
                }
                response.getOrNull()?.userinfo?.let {
                    _loginSuccess.value = UserToUserUiModelMapper.map(it)
                }
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