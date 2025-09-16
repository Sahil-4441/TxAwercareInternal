package com.example.avercare.core.util

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import kotlin.text.indexOf

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun TextView.isBlank(): Boolean = text.toString().trim().isEmpty()

fun TextView.content(): String = text.toString().trim()

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

fun EditText.otpHelper() {

    setOnKeyListener { v, keyCode, event ->

        if (keyCode == KeyEvent.KEYCODE_DEL) {
            if (isBlank()) {
                val view = focusSearch(View.FOCUS_LEFT)
                view?.requestFocus()

            }
        }

        false
    }
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s.toString().length == 1) {
                //  background = ContextCompat.getDrawable(context, R.drawable.custom_box_bg)
                val view = focusSearch(View.FOCUS_RIGHT)
                view?.let { it.requestFocus() } ?: run {
                    this@otpHelper.hideKeyBoard()
                }
            } else {
                //     background = ContextCompat.getDrawable(context, R.drawable.custom_box_bg)

            }

        }
    })


}

fun TextView.makeTextLink(
    str: String,
    underlined: Boolean,
    color: Int?,
    action: (() -> Unit)? = null
) {
    val spannableString = SpannableString(text)
    val textColor = color ?: currentTextColor
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(textView: View) {
            action?.invoke()
        }

        override fun updateDrawState(drawState: TextPaint) {
            super.updateDrawState(drawState)
            drawState.isUnderlineText = underlined
            drawState.color = textColor
        }
    }
    val index = spannableString.indexOf(str)
    spannableString.setSpan(
        clickableSpan,
        index,
        index + str.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    text = spannableString
    movementMethod = LinkMovementMethod.getInstance()
    highlightColor = Color.TRANSPARENT

}

fun View.hideKeyBoard() {

    this.let {
        val imm =
            this.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(it.windowToken, 0)
    }
}