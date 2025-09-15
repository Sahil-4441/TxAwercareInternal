package com.example.cleanarchitecture.core.base

import android.widget.CompoundButton
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanarchitecture.core.util.FormErrors
import com.example.cleanarchitecture.core.util.Validations
import com.example.cleanarchitecture.core.util.Validations.isPasswordValid
import com.example.cleanarchitecture.domain.model.request.SignUpRequest
import com.google.gson.internal.LinkedTreeMap

abstract class BaseViewModel : ViewModel() {
    var formErrors = ObservableArrayList<FormErrors>()
    var termsConditionChecked = false

    var signUpRequestData: MutableLiveData<SignUpRequest> =
        MutableLiveData<SignUpRequest>().apply {
            value = SignUpRequest()
        }


    fun isSignupFormValid(): Boolean {
        formErrors.clear()
       if (signUpRequestData.value?.email?.trim().isNullOrEmpty()) {
            formErrors.add(FormErrors.MISSING_EMAIL)
        } else if (!Validations.isValidEmail(signUpRequestData.value?.email!!)) {
            formErrors.add(FormErrors.INVALID_EMAIL)
        } else if (signUpRequestData.value?.password?.trim().isNullOrEmpty()) {
            formErrors.add(FormErrors.MISSING_PASSWORD)
        } else if (!isPasswordValid(signUpRequestData.value?.password!!)) {
            formErrors.add(FormErrors.INVALID_PASSWORD)
        }
        return formErrors.isEmpty()
    }

    fun onTermsConditionChecked(buttonView: CompoundButton, isChecked: Boolean) {
        termsConditionChecked = isChecked
    }
}
