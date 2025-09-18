package com.example.avercare.core.util

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.avercare.R
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("imageFromResource")
fun bindImageFromResource(imageView: ImageView, resourceId: Int?) {
    if (resourceId != null) {
        imageView.setImageResource(resourceId)
    }
}
@BindingAdapter("app:enabledAlpha")
fun setEnabledAlpha(view: View, isEnabled: Boolean) {
    view.alpha = if (isEnabled) 1f else 0.6f
    view.isClickable = isEnabled
}

@BindingAdapter("app:boxErrorState")
fun setBoxErrorState(layout: TextInputLayout, hasError: Boolean) {
    val context = layout.context
    layout.boxBackgroundColor = if (hasError) {
        ContextCompat.getColor(context, R.color.dark_red_color)
    } else {
        ContextCompat.getColor(context, R.color.secondary_dark_grey)
    }
}




