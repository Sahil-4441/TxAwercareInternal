package com.example.avercare.presentation.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.avercare.R
import com.example.avercare.core.base.BaseFragment
import com.example.avercare.core.util.LoginField
import com.example.avercare.core.util.SignupField
import com.example.avercare.core.util.makeTextLink
import com.example.avercare.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>() {

    private val vmSignUp: SignUpViewModel by viewModels()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSignupBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vmSignUp = vmSignUp
        binding.lifecycleOwner = viewLifecycleOwner
        initUi()
        setClick()
        manageObservers()
    }

    private fun initUi() {
        binding.tvPrivacyTerms.makeTextLink(
            resources.getString(R.string.terms_of_service),
            false,
            ContextCompat.getColor(
                requireContext(),
                R.color.primaryColor
            ),
            action = {

            })
        binding.tvPrivacyTerms.makeTextLink(
            resources.getString(R.string.privacy_policy),
            false,
            ContextCompat.getColor(
                requireContext(),
                R.color.primaryColor
            ),
            action = {

            })

        binding.tvAlreadyHaveAccount.makeTextLink(
            resources.getString(R.string.login),
            false,
            ContextCompat.getColor(
                requireContext(),
                R.color.primaryColor
            ),
            action = {
                findNavController().navigate(SignupFragmentDirections.actionSignupToLogin())
            })

        binding.tvSecureAccountMfa.makeTextLink(
            resources.getString(R.string.secure_account_with_mfa_method),
            true,
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            ),
            action = {

            })
        binding.tvSecureAccountMfa.makeTextLink(
            resources.getString(R.string.mfa_verification),
            true,
            ContextCompat.getColor(
                requireContext(),
                R.color.primaryColor
            ),
            action = {

            })


    }

    private fun setClick() {
        binding.btnSignUp.setOnClickListener {
            vmSignUp.validateSignUp()

            if (!vmSignUp.termsConditionChecked) {
                showError(resources.getString(R.string.please_agree_terms_of_service))
                return@setOnClickListener
            }

            if (vmSignUp.isFormValid.value) {
                val email = vmSignUp.email.value
                val password = vmSignUp.password.value
                val confirmPassword = vmSignUp.confirmPassword.value
                showError("Email: $email\nPassword: $password ConfirmPassword: $confirmPassword")
                // Navigate to next screen
            }
        }

    }

    private fun manageObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            /*     vmSignUp.authState.collectLatest { state ->
                     when (state) {
                         is Resource.Idle -> {
                             hideLoading()
                         }
                         is Resource.Loading -> {
                             showLoading()
                         }
                         is Resource.Success -> {
                             hideLoading()
                             findNavController().popBackStack()
                         }
                         is Resource.Error -> {
                             hideLoading()
                             showError(state.message)
                         }
                     }
                 }*/
        }

        viewLifecycleOwner.lifecycleScope.launch {
            vmSignUp.validationError.collect { error ->
                when (error?.first) {
                    SignupField.EMAIL -> binding.emailLayout.error = getString(error.second)
                    SignupField.PASSWORD -> binding.passwordLayout.error = getString(error.second)
                    SignupField.CONFIRM_PASSWORD -> binding.confirmPasswordLayout.error =
                        getString(error.second)

                    null -> {
                        binding.emailLayout.error = null
                        binding.passwordLayout.error = null
                        binding.confirmPasswordLayout.error = null
                    }

                }
            }
        }
    }
}