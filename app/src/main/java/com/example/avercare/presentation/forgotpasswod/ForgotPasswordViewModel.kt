package com.example.avercare.presentation.forgotpasswod

import com.example.avercare.core.base.BaseViewModel
import com.example.avercare.domain.repository.AuthRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@AndroidEntryPoint
class ForgotPasswordViewModel @Inject constructor(
    private val repository : AuthRepository
) : BaseViewModel() {

    private val _validationError = MutableSharedFlow<Int>()
    val validationError = _validationError.asSharedFlow()

    fun validateForgotPasswordAccount(
        email : String ){

    }
}