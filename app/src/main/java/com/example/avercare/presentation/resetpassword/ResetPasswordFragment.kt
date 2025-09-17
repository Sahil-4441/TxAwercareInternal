package com.example.avercare.presentation.resetpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.avercare.R
import com.example.avercare.core.base.BaseFragment
import com.example.avercare.databinding.FragmentResetPasswordBinding

class ResetPasswordFragment : BaseFragment<FragmentResetPasswordBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentResetPasswordBinding =
        FragmentResetPasswordBinding.inflate(inflater, container, false)


}