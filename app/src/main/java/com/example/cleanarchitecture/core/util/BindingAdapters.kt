package com.example.cleanarchitecture.core.util

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import com.example.cleanarchitecture.R
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("app:errorEmailText")
fun emailValidator(view: TextInputLayout, errorMessage: ObservableArrayList<FormErrors>) {
    when {
        errorMessage.contains(FormErrors.MISSING_EMAIL) -> {
            view.error = view.context.getString(R.string.please_enter_email_address)
        }

        errorMessage.contains(FormErrors.INVALID_EMAIL) -> {
            view.error = view.context.getString(R.string.please_enter_valid_email_address)
        }

        else -> {
            view.error = ""
        }
    }
}


@BindingAdapter("app:errorPasswordText")
fun passwordValidator(view: TextInputLayout, errorMessage: ObservableArrayList<FormErrors>) {
    when {
        errorMessage.contains(FormErrors.MISSING_PASSWORD) -> {
            view.error = view.context.getString(R.string.please_enter_password)
        }

        errorMessage.contains(FormErrors.INVALID_PASSWORD) -> {
            view.error = view.context.getString(R.string.please_enter_valid_password)
        }

        else -> {
            view.error = ""
        }
    }
}

@BindingAdapter("app:errorConfirmPasswordText")
fun confirmPasswordValidator(view: TextInputLayout, errorMessage: ObservableArrayList<FormErrors>) {
    when {
        errorMessage.contains(FormErrors.MISSING_CONFIRM_PASSWORD) -> {
            view.error = view.context.getString(R.string.please_confirm_enter_password)
        }

        errorMessage.contains(FormErrors.PASSWORDS_NOT_MATCHING) -> {
            view.error = view.context.getString(R.string.create_confirm_not_match)
        }

        else -> {
            view.error = ""
        }
    }
}
