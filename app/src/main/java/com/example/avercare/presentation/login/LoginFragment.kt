package com.example.avercare.presentation.login

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.avercare.R
import com.example.avercare.core.base.BaseFragment
import com.example.avercare.core.util.makeTextLink
import com.example.avercare.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val vmLogin: LoginViewModel by viewModels()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentLoginBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        setClick()
        manageObservers()
    }

    private fun setClick() {
        binding.btnSubmit.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            lifecycleScope.launch {
                vmLogin.validateLogin(
                    email,
                    password
                )
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
            /*     vmLogin.authState.collectLatest { state ->
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
            vmLogin.validationError.collectLatest { errorResId ->
                showError(getString(errorResId))
            }
        }
    }
}