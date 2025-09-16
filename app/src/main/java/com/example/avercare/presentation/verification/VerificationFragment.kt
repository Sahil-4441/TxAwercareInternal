package com.example.avercare.presentation.verification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avercare.core.base.BaseFragment
import com.example.avercare.databinding.FragmentVerificationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerificationFragment : BaseFragment<FragmentVerificationBinding>(),
    VerificationAdapter.VerificationListener {
    private var verificationAdapter: VerificationAdapter? = null


    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentVerificationBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        val list = getVerificationItem(requireContext())
        binding.rvVerification.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        verificationAdapter = VerificationAdapter(this, list.toMutableList())
        binding.rvVerification.adapter = verificationAdapter
    }

    override fun onItemClick(
        position: Int,
        data: VerificationModel
    ) {
        findNavController().navigate(
            VerificationFragmentDirections.actionVerificationFragmentToVerifyOtpFragment()
        )
    }

}