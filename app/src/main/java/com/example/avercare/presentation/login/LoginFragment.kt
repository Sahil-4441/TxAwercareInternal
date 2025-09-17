package com.example.avercare.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.avercare.R
import com.example.avercare.core.base.BaseFragment
import com.example.avercare.core.util.setSpannableText
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
        setSpannableText(
            binding.tvDontHaveAccount,
            getString(R.string.dont_have_account),
            getString(R.string.signup),
            R.color.primaryColor
        ) {
            findNavController().navigate(R.id.action_login_to_signup)

        }
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