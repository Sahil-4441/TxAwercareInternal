package com.example.cleanarchitecture.presentation.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.core.base.BaseFragment
import com.example.cleanarchitecture.core.util.setSpannableText
import com.example.cleanarchitecture.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>() {
    //private val viewModel: AuthViewModel by viewModels()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSignupBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observable()
        setStyling()
    }

    private fun setStyling() {
        setSpannableText(
            binding.tvAlreadyHaveAccount,
            getString(R.string.already_have_account),
            getString(R.string.login),
            R.color.primaryColor
        ) {
            findNavController().navigate(R.id.action_signup_to_login)
        }
    }

    private fun observable() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.authState.collectLatest { state ->
//                when (state) {
//                    is Resource.Idle -> {
//                        hideLoading()
//                    }
//                    is Resource.Loading -> {
//                        showLoading()
//                    }
//                    is Resource.Success -> {
//                        hideLoading()
//                        findNavController().popBackStack()
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