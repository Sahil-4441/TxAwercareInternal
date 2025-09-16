package com.example.avercare.core.util

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import kotlin.text.indexOf

fun Context.showToast(message: String){
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
}

fun setSpannableText(
    textView: TextView,
    fullText: String,
    clickablePart: String,
    @ColorRes colorRes: Int,
    onClick: () -> Unit
) {
    val spannable = SpannableString(fullText)

    val startIndex = fullText.indexOf(clickablePart)
    if (startIndex == -1) {
        textView.text = fullText
        return
    }
    val endIndex = startIndex + clickablePart.length

    // Apply color
    val colorSpan = ForegroundColorSpan(ContextCompat.getColor(textView.context, colorRes))
    spannable.setSpan(colorSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

    // Apply clickable
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View) {
            onClick()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = false
        }
    }
    spannable.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

    textView.text = spannable
    textView.movementMethod = LinkMovementMethod.getInstance()
}