package com.example.cleanarchitecture.core.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.graphics.drawable.toDrawable
import com.example.cleanarchitecture.R

@Singleton
class ProgressBarDialog @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private var dialog: Dialog? = null
    private var progressBar: ProgressBar? = null

    fun showProgressBar(title: String? = null) {
        if (dialog?.isShowing == true) return

        val msg = if (title.isNullOrBlank()) {
            context.getString(R.string.loading)
        } else {
            title
        }

        try {
            dialog = Dialog(context, R.style.Theme_App).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setCancelable(false)
                setCanceledOnTouchOutside(false)
                setContentView(R.layout.dialog_progress_bar)

                window?.apply {
                    setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
                    addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    statusBarColor = ContextCompat.getColor(context, R.color.primaryColor)

                    val lp = attributes
                    lp.dimAmount = 0.4f
                    attributes = lp
                }

                // Bind UI
                val tvProgressText: TextView = findViewById(R.id.tvProgressText)
                progressBar = findViewById(R.id.avi)

                tvProgressText.text = msg
                tvProgressText.visibility = if (msg.isEmpty()) View.GONE else View.VISIBLE
                progressBar?.visibility = View.VISIBLE

                show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun dismissProgressDialog() {
        try {
            dialog?.let {
                if (it.isShowing) {
                    it.dismiss()
                }
            }
            dialog = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}

