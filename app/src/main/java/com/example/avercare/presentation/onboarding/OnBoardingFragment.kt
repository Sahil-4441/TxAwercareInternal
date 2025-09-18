package com.example.avercare.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.avercare.R
import com.example.avercare.core.base.BaseFragment
import com.example.avercare.core.util.makeTextLink
import com.example.avercare.databinding.FragmentOnBoardingBinding

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
        binding.tvAlreadyHaveAccount.makeTextLink(
            resources.getString(R.string.login),
            false,
            ContextCompat.getColor(
                requireContext(),
                R.color.primaryColor
            ),
            action = {
                findNavController().navigate(
                    OnBoardingFragmentDirections.actionOnBoardingFragmentToLoginFragment()
                )
            }
        )
    }

    private fun clickListeners() {
        binding.btnStart.setOnClickListener {
            findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingToSignup())
        }
    }

}