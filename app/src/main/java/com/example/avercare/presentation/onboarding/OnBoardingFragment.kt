package com.example.avercare.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.avercare.R
import com.example.avercare.core.base.BaseFragment
import com.example.avercare.databinding.FragmentOnBoardingBinding
import com.example.avercare.core.util.setSpannableText

class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOnBoardingBinding = FragmentOnBoardingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStyling()
        clickListeners()
    }

    private fun setStyling() {
        setSpannableText(
            binding.tvAlreadyHaveAccount,
            getString(R.string.already_have_account),
            getString(R.string.login),
            R.color.primaryColor
        ){
            findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
        }
    }

    private fun clickListeners() {
        binding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_onBoarding_to_signup)
        }
    }

}