package com.example.avercare.presentation.resetpassword

import com.example.avercare.R
import com.example.avercare.core.base.BaseViewModel
import com.example.avercare.core.util.LoginField
import com.example.avercare.core.util.ResetPasswordField
import com.example.avercare.core.util.isValidPassword
import com.example.avercare.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val repository: AuthRepository
): BaseViewModel(){

    private val _validationError = MutableStateFlow<Pair<ResetPasswordField,Int>?>(null)
    val validationError : StateFlow<Pair<ResetPasswordField, Int>?> = _validationError

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private fun validatePassword(){
        when {
            _password.value.isEmpty() -> _validationError.value =
                ResetPasswordField.PASSWORD to R.string.password_is_required

            !_password.value.isValidPassword() -> _validationError.value =
                ResetPasswordField.PASSWORD to R.string.please_enter_valid_password

            else -> if (_validationError.value?.first == ResetPasswordField.PASSWORD) _validationError.value = null
        }
    }



    fun validateForm(){
        validatePassword()
    }

}