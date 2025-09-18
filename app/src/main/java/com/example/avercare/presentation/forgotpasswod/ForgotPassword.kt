package com.example.avercare.presentation.forgotpasswod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.avercare.R
import com.example.avercare.core.base.BaseFragment
import com.example.avercare.databinding.FragmentForgotPasswordBinding


class ForgotPassword : BaseFragment<FragmentForgotPasswordBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentForgotPasswordBinding  =
        FragmentForgotPasswordBinding.inflate(inflater,container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListeners()
    }

    private fun clickListeners() {
        binding.clBtnLayout.setOnClickListener {
            findNavController().navigate(ForgotPasswordDirections.actionForgotPasswordToResetPasswordFragment())
        }
    }

}