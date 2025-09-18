package com.example.avercare.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.avercare.R
import com.example.avercare.core.base.BaseFragment
import com.example.avercare.core.util.LoginField
import com.example.avercare.core.util.makeTextLink
import com.example.avercare.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val vmLogin: LoginViewModel by viewModels()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentLoginBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vmLogin = vmLogin
        binding.lifecycleOwner = viewLifecycleOwner
        initUi()
        setClick()
        manageObservers()
    }

    private fun setClick() {
        binding.btnLogin.setOnClickListener {
            vmLogin.validateLogin()

            // Terms condition check
            if (!vmLogin.termsConditionChecked) {
                showError(resources.getString(R.string.please_agree_terms_of_service))
                return@setOnClickListener
            }

            if (vmLogin.isFormValid.value) {
                val email = vmLogin.email.value
                val password = vmLogin.password.value
                showError("Email: $email\nPassword: $password")
                // Navigate to next screen
            }
        }

        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPassword())
        }

    }

    private fun initUi() {
        binding.tvDontHaveAccount.makeTextLink(
            resources.getString(R.string.signup),
            false,
            ContextCompat.getColor(
                requireContext(),
                R.color.primaryColor
            ),
            action = {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginToSignup()
                )
            }
        )


        binding.tvMfaVerify.makeTextLink(
            resources.getString(R.string.try_another_mfa_method),
            true,
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            ),
            action = {

            }
        )

        binding.tvMfaVerify.makeTextLink(
            resources.getString(R.string.mfa_verification),
            false,
            ContextCompat.getColor(
                requireContext(),
                R.color.primaryColor
            ),
            action = {

            }
        )

        binding.tvPrivacyTerms.makeTextLink(
            resources.getString(R.string.terms_of_service),
            false,
            ContextCompat.getColor(
                requireContext(),
                R.color.primaryColor
            ),
            action = {

            }
        )

        binding.tvPrivacyTerms.makeTextLink(
            resources.getString(R.string.privacy_policy),
            false,
            ContextCompat.getColor(
                requireContext(),
                R.color.primaryColor
            ),
            action = {

            }
        )

    }

    private fun manageObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            vmLogin.validationError.collect { error ->
                when (error?.first) {
                    LoginField.EMAIL -> binding.emailLayout.error = getString(error.second)
                    LoginField.PASSWORD -> binding.passwordLayout.error = getString(error.second)
                    null -> {
                        binding.emailLayout.error = null
                        binding.passwordLayout.error = null
                    }

                }
            }
        }
    }
}