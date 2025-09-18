package com.example.avercare.presentation.signup

import android.widget.CompoundButton
import androidx.lifecycle.viewModelScope
import com.example.avercare.R
import com.example.avercare.core.base.BaseViewModel
import com.example.avercare.core.util.SignupField
import com.example.avercare.core.util.isValidEmail
import com.example.avercare.core.util.isValidPassword
import com.example.avercare.data.remote.Resource
import com.example.avercare.domain.model.User
import com.example.avercare.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val _signUpState = MutableStateFlow<Resource<User>>(Resource.Idle)
    val signUpState: StateFlow<Resource<User>> = _signUpState
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword

    private val _validationError = MutableStateFlow<Pair<SignupField, Int>?>(null)
    val validationError: StateFlow<Pair<SignupField, Int>?> = _validationError

    private val _isFormValid = MutableStateFlow(false)
    val isFormValid: StateFlow<Boolean> = _isFormValid

    val emailHasError: StateFlow<Boolean> = _validationError
        .map { it?.first == SignupField.EMAIL }
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    val passwordHasError: StateFlow<Boolean> = _validationError
        .map { it?.first == SignupField.PASSWORD }
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    val confirmPasswordHasError: StateFlow<Boolean> = _validationError
        .map { it?.first == SignupField.CONFIRM_PASSWORD }
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    var termsConditionChecked = false
    fun isTermsConditionChecked(buttonView: CompoundButton, isChecked: Boolean) {
        termsConditionChecked = isChecked
    }

    fun onEmailChanged(text: CharSequence?) {
        _email.value = text?.toString().orEmpty()
        validateEmail()
        validateForm()
    }

    fun onPasswordChanged(text: CharSequence?) {
        _password.value = text?.toString().orEmpty()
        validatePassword()
        validateForm()
    }

    fun onConfirmPasswordChanged(text: CharSequence?) {
        _confirmPassword.value = text?.toString().orEmpty()
        validateConfirmPassword()
        validateForm()
    }

    private fun validateEmail() {
        when {
            _email.value.isEmpty() -> _validationError.value =
                SignupField.EMAIL to R.string.email_is_required

            !_email.value.isValidEmail() -> _validationError.value =
                SignupField.EMAIL to R.string.please_enter_a_valid_email

            else -> if (_validationError.value?.first == SignupField.EMAIL) _validationError.value = null
        }
    }

    private fun validatePassword() {
        when {
            _password.value.isEmpty() -> _validationError.value =
                SignupField.PASSWORD to R.string.password_is_required

            !_password.value.isValidPassword() -> _validationError.value =
                SignupField.PASSWORD to R.string.please_enter_valid_password

            else -> if (_validationError.value?.first == SignupField.PASSWORD) _validationError.value = null
        }
    }

    private fun validateConfirmPassword() {
        when {
            _confirmPassword.value.isEmpty() -> _validationError.value =
                SignupField.CONFIRM_PASSWORD to R.string.confirm_password_is_required

            _confirmPassword.value != _password.value -> _validationError.value =
                SignupField.CONFIRM_PASSWORD to R.string.password_not_match

            else -> if (_validationError.value?.first == SignupField.CONFIRM_PASSWORD) _validationError.value = null
        }
    }

    private fun validateForm() {
        _isFormValid.value = _email.value.isNotEmpty() && _email.value.isValidEmail() &&
                _password.value.isNotEmpty() && _password.value.isValidPassword() &&
                _confirmPassword.value.isNotEmpty() && _confirmPassword.value == _password.value
    }

    fun validateSignUp() {
        validateEmail()
        validatePassword()
        validateConfirmPassword()
        validateForm()
    }

}
