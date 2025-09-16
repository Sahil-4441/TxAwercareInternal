package com.example.cleanarchitecture.presentation.auth.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.core.base.BaseFragment
import com.example.cleanarchitecture.core.util.setSpannableText
import com.example.cleanarchitecture.databinding.FragmentOnBoardingBinding
import com.example.cleanarchitecture.databinding.FragmentSignupBinding

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