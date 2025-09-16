package com.example.avercare.core.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageFromResource")
fun bindImageFromResource(imageView: ImageView, resourceId: Int?) {
    if (resourceId != null) {
        imageView.setImageResource(resourceId)
    }
}