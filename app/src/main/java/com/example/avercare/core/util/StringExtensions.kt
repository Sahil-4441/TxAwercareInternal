package com.example.avercare.core.util

import java.util.regex.Pattern

fun String.isValidEmail(): Boolean {
    if (this.isEmpty()) return true
    val emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"
    return matches(emailPattern.toRegex())  // Validate the email format
}

fun String.isValidPassword(): Boolean {
    val regExpn = "^(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$"
    val pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)  // `this` refers to the string the method is called on
    return matcher.matches()
}


fun String.isValidPhoneNumber(): Boolean {
    val phonePattern = "^[+]?[0-9]{5,15}$"
    return this.length in 5..15 && matches(phonePattern.toRegex())
}






