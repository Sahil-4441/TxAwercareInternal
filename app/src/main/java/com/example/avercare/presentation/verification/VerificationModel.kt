package com.example.avercare.presentation.verification

import android.content.Context
import com.example.avercare.R

data class VerificationModel(
    var title: String,
    var subTitle: String,
    var icon: Int,
)

fun getVerificationItem(context: Context): List<VerificationModel> {
    val verificationItems = mutableListOf(
        VerificationModel(
            title = context.getString(R.string.text_message),
            subTitle = context.getString(R.string.send_a_code_to_your_phone),
            icon = R.drawable.ic_vrfy_sms
        ),
        VerificationModel(
            title = context.getString(R.string.email),
            subTitle = context.getString(R.string.send_a_code_to_your_email),
            icon = R.drawable.ic_vrfy_email
        ),
        VerificationModel(
            title = context.getString(R.string.google_authenticator),
            subTitle = context.getString(R.string.use_your_google_authenticator_app),
            icon = R.drawable.ic_vrfy_google_auth
        )

    )
    return verificationItems
}
