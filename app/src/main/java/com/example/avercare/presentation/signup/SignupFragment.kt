package com.example.avercare.presentation.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.core.content.ContextCompat
import com.example.avercare.R
import com.example.avercare.core.base.BaseFragment
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

            })

    }

    private fun setClick() {
        binding.btnSubmit.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()
            lifecycleScope.launch {
                vmSignUp.validateSignUpAccount(
                    email,
                    password,
                    confirmPassword
                )
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
            vmSignUp.validationError.collectLatest { errorResId ->
                showError(getString(errorResId))
            }
        }
    }


}