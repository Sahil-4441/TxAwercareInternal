package com.example.avercare.presentation.letstart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.avercare.R
import com.example.avercare.core.base.BaseFragment
import com.example.avercare.core.util.makeTextLink
import com.example.avercare.databinding.FragmentLetStartBinding

class LetStartFragment : BaseFragment<FragmentLetStartBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLetStartBinding = FragmentLetStartBinding.inflate(inflater, container, false)

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
                    LetStartFragmentDirections.actionOnBoardingFragmentToLoginFragment()
                )
            }
        )
    }

    private fun clickListeners() {
        binding.btnStart.setOnClickListener {
            findNavController().navigate(LetStartFragmentDirections.actionOnBoardingToSignup())
        }
    }

}