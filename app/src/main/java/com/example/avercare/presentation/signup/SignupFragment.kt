package com.example.avercare.presentation.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.avercare.R
import com.example.avercare.core.base.BaseFragment
import com.example.avercare.core.util.makeTextLink
import com.example.avercare.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>() {
    //private val viewModel: AuthViewModel by viewModels()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSignupBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observable()

    }

    private fun initUI() {
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