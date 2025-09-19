package com.example.avercare.presentation.forgotpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.avercare.core.base.BaseFragment
import com.example.avercare.core.util.ForgotPasswordField
import com.example.avercare.databinding.FragmentForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForgotPassword : BaseFragment<FragmentForgotPasswordBinding>() {

    private val vmForgotPassword : ForgotPasswordViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentForgotPasswordBinding  =
        FragmentForgotPasswordBinding.inflate(inflater,container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vmForgotPassword = vmForgotPassword
        binding.lifecycleOwner = viewLifecycleOwner
        clickListeners()
        manageObservers()
    }

    private fun manageObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            vmForgotPassword.validationError.collect { error ->
                when (error?.first){
                    ForgotPasswordField.EMAIL -> binding.emailLayout.error = getString( error.second)
                    null -> {
                        binding.emailLayout.error = null
                    }
                }
            }
        }
    }

    private fun clickListeners() {
        binding.clBtnSubmit.setOnClickListener {
            vmForgotPassword.validateForgotPassword()
            if(vmForgotPassword.isFormValid.value){
                val email = vmForgotPassword.email.value
                showError("Email: $email")
                findNavController().navigate(
                    ForgotPasswordDirections.actionForgotPasswordToResetPasswordFragment())
            }
        }
    }

}