package com.example.cleanarchitecture.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.cleanarchitecture.core.util.ProgressBarDialog

abstract class BaseActivity<VB: ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: VB
    lateinit var progressBarDialog: ProgressBarDialog

    abstract fun getViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        progressBarDialog = ProgressBarDialog(this)

    }

    fun showLoading(message: String?) {
        progressBarDialog.showProgressBar(message)
    }


    fun hideLoading() {
        progressBarDialog.dismissProgressDialog()
    }

}
