package com.example.avercare.presentation.forgotpassword

import androidx.lifecycle.viewModelScope
import com.example.avercare.R
import com.example.avercare.core.base.BaseViewModel
import com.example.avercare.core.util.ForgotPasswordField
import com.example.avercare.core.util.LoginField
import com.example.avercare.core.util.isValidEmail
import com.example.avercare.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val repository : AuthRepository
) : BaseViewModel() {

    private val _email = MutableStateFlow("")
    val email : StateFlow<String> = _email
    private val _validationError = MutableStateFlow<Pair<ForgotPasswordField, Int>?>(null)
    val validationError : StateFlow<Pair<ForgotPasswordField, Int>?> = _validationError

    private val _isFormValid = MutableStateFlow(false)
    val isFormValid : StateFlow<Boolean> = _isFormValid

    val emailHasError : StateFlow<Boolean> = _validationError
        .map { it?.first == ForgotPasswordField.EMAIL }
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    private fun validateEmail() {
        when{
            _email.value.isEmpty() -> _validationError.value =
                ForgotPasswordField.EMAIL to R.string.email_is_required

            !_email.value.isValidEmail() -> _validationError.value =
                ForgotPasswordField.EMAIL to R.string.please_enter_a_valid_email

            else -> if (_validationError.value?.first == ForgotPasswordField.EMAIL) _validationError.value = null

        }
    }

    fun onEmailChanged(text: CharSequence?) {
        _email.value = text?.toString().orEmpty()
        validateEmail()
        validateForm()
    }

    private fun validateForm(){
        _isFormValid.value = _email.value.isNotEmpty() && _email.value.isValidEmail()
    }

    fun validateForgotPassword(){
        validateEmail()
        validateForm()
    }

}