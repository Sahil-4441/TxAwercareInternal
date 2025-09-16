package com.example.avercare.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.avercare.R
import com.example.avercare.core.base.BaseFragment
import com.example.avercare.core.util.setSpannableText
import com.example.avercare.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
   // private val viewModel: AuthViewModel by viewModels()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentLoginBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observable()
        setStyling()
        clickListeners()
    }

    private fun clickListeners() {
        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPassword)
        }
    }

    private fun setStyling() {
        setSpannableText(
            binding.tvDontHaveAccount,
            getString(R.string.dont_have_account),
            getString(R.string.signup),
            R.color.primaryColor
        ) {
            findNavController().navigate(R.id.action_login_to_signup)

        }
    }

    private fun observable() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.authState.collectLatest { state ->
//                when (state) {
//                    is Resource.Idle -> {
//                        hideLoading()
//                    }
//                    is Resource.Loading -> showLoading()
//                    is Resource.Success -> {
//                        hideLoading()
//                        findNavController().navigate(LoginFragmentDirections.actionLoginToExample())
//                    }
//                    is Resource.Error -> {
//                        hideLoading()
//                        showError(state.message)
//                    }
//                }
//            }
//        }
    }
}