package com.example.avercare.presentation.verifyOtp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.avercare.R
import com.example.avercare.core.base.BaseFragment
import com.example.avercare.core.util.content
import com.example.avercare.core.util.makeTextLink
import com.example.avercare.core.util.otpHelper
import com.example.avercare.databinding.FragmentVerifyOtpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyOtpFragment : BaseFragment<FragmentVerifyOtpBinding>() {
    private var otp: String? = null

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentVerifyOtpBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        keyListener()
        setClick()
    }

    private fun initUi() {
        binding.tvResendOtp.makeTextLink(
            resources.getString(R.string.resend),
            false,
            ContextCompat.getColor(
                requireContext(),
                R.color.primaryColor
            ),
            action = {
                clearOtp()
            })
    }

    private fun setClick() {
        binding.btnVerify.setOnClickListener {
            otp = with(binding) {
                firstOtpET.content() + secondOtpET.content() +
                        thirdOtpET.content() + fourthOtpET.content() +
                        fifthOtpET.content() + sixOtpET.content()
            }
            if (otp!!.isBlank()) {
                showError(requireContext().getString(R.string.empty_otp))
            } else if (otp!!.length < 6) {
                showError(requireContext().getString(R.string.invalid_otp))
            } else {
                //Otp is valid
            }
        }

    }

    private fun keyListener() {
        binding.firstOtpET.otpHelper()
        binding.secondOtpET.otpHelper()
        binding.thirdOtpET.otpHelper()
        binding.fourthOtpET.otpHelper()
        binding.fifthOtpET.otpHelper()
        binding.sixOtpET.otpHelper()
    }

    private fun clearOtp() {
        binding.firstOtpET.setText("")
        binding.secondOtpET.setText("")
        binding.thirdOtpET.setText("")
        binding.fourthOtpET.setText("")
        binding.firstOtpET.requestFocus()
    }

}