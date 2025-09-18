package com.example.avercare.core.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ReplacementSpan
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.avercare.R
import kotlin.text.indexOf

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun TextView.isBlank(): Boolean = text.toString().trim().isEmpty()

fun TextView.content(): String = text.toString().trim()

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
                background = ContextCompat.getDrawable(context, R.drawable.rounded_otp_edt_view)
                val view = focusSearch(View.FOCUS_RIGHT)
                view?.let { it.requestFocus() } ?: run {
                    this@otpHelper.hideKeyBoard()
                }
            } else {
                background = ContextCompat.getDrawable(context, R.drawable.rounded_otp_edt_view)

            }

        }
    })
}

fun TextView.makeTextLink(
    str: String,
    underlined: Boolean,
    color: Int?,
    underlineColor: Int? = null,
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
            drawState.color = textColor
            drawState.isUnderlineText = false // disable default underline
        }
    }

    // Custom underline span
    val underlineSpan = object : ReplacementSpan() {
        override fun getSize(
            paint: Paint,
            text: CharSequence,
            start: Int,
            end: Int,
            fm: Paint.FontMetricsInt?
        ): Int {
            return paint.measureText(text, start, end).toInt()
        }

        override fun draw(
            canvas: Canvas,
            text: CharSequence,
            start: Int,
            end: Int,
            x: Float,
            top: Int,
            y: Int,
            bottom: Int,
            paint: Paint
        ) {
            val textStr = text.subSequence(start, end).toString()
            paint.color = textColor
            canvas.drawText(textStr, x, y.toFloat(), paint)

            if (underlined) {
                val underlinePaint = Paint(paint)
                underlinePaint.style = Paint.Style.STROKE
                underlinePaint.strokeWidth = 2f
                underlinePaint.color = underlineColor ?: textColor

                val textWidth = paint.measureText(textStr)
                val underlineY = y + 6f
                canvas.drawLine(x, underlineY, x + textWidth, underlineY, underlinePaint)
            }
        }
    }

    val index = spannableString.indexOf(str)
    if (index != -1) {
        spannableString.setSpan(
            clickableSpan,
            index,
            index + str.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            underlineSpan,
            index,
            index + str.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

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