package com.example.avercare.presentation.auth

import androidx.lifecycle.viewModelScope
import com.example.avercare.core.base.BaseViewModel
import com.example.avercare.domain.model.User
import com.example.avercare.domain.repository.AuthRepository
import com.example.avercare.data.remote.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {
    private val _authState = MutableStateFlow<Resource<User>>(Resource.Idle)
    val authState: StateFlow<Resource<User>> = _authState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password)
                .onStart { _authState.value = Resource.Loading }
                .catch { e -> _authState.value = Resource.Error(e.message ?: "Unknown error") }
                .collect { user ->
                    //save(user)
                    _authState.value = Resource.Success(user)
                }
        }
    }

    fun signup(name: String, email: String, password: String) {
        viewModelScope.launch {
            authRepository.signup(name, email, password)
                .onStart { _authState.value = Resource.Loading }
                .catch { e -> _authState.value = Resource.Error(e.message ?: "Unknown error") }
                .collect { user ->
                    //save(user)
                    _authState.value = Resource.Success(user)
                }
        }
    }

    fun loadCurrentUser() {
        viewModelScope.launch {
            authRepository.getUser().collect { user ->
                if (user != null) _authState.value = Resource.Success(user)
            }
        }
    }

    private fun save(user: User) {
        viewModelScope.launch { authRepository.saveUser(user) }
    }
}

