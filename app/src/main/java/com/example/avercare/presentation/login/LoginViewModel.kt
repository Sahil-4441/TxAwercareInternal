package com.example.avercare.presentation.login

import android.widget.CompoundButton
import androidx.lifecycle.viewModelScope
import com.example.avercare.R
import com.example.avercare.core.base.BaseViewModel
import com.example.avercare.core.util.isValidEmail
import com.example.avercare.core.util.isValidPassword
import com.example.avercare.data.remote.Resource
import com.example.avercare.domain.model.User
import com.example.avercare.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val _loginState = MutableStateFlow<Resource<User>>(Resource.Idle)
    val loginState: StateFlow<Resource<User>> = _loginState
    private val _validationError = MutableSharedFlow<Int>()
    val validationError = _validationError.asSharedFlow()

    fun validateLogin(
        email: String,
        password: String,
    ) {
        viewModelScope.launch {
            when {
                email.isEmpty() -> _validationError.emit(R.string.email_is_required)
                !email.isValidEmail() -> _validationError.emit(R.string.please_enter_a_valid_email)
                password.isEmpty() -> _validationError.emit(R.string.password_is_required)
                !password.isValidPassword() -> _validationError.emit(R.string.please_enter_valid_password)
                else -> {
                    // valid → signup logic
                }
            }
        }
    }

}
