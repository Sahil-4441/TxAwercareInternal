package com.example.cleanarchitecture.core.util

import android.text.TextUtils
import android.util.Log
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.text.isEmpty
import kotlin.text.trim


object Validations {
    const val PHONE_NUMBER_REGEX = "[0]*[1-9][0-9]*"

    private val EMAIL_ADDRESS: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    fun isValidEmail(email: String): Boolean {
        return EMAIL_ADDRESS.matcher(email.trim()).matches()
    }

    fun isTrimEmpty(value: String): Boolean {
        return value.trim().isEmpty()
    }

    fun isPasswordValid(password: String): Boolean {
        val regExpn = "^(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$"
        val inputStr: CharSequence = password
        val pattern: Pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(inputStr)
        return matcher.matches()
    }


    fun isContainNumberCapital(password: String): Boolean {
        val pattern = Regex(".*\\d+.*")
        Log.e("isContainNumberCapital", pattern.containsMatchIn(password).toString())
        return (pattern.containsMatchIn(password))
    }

    fun isFieldEmpty(value: String?): Boolean {
        return TextUtils.isEmpty(value?.trim())
    }


}

