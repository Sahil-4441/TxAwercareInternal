package com.example.avercare.presentation.profileManagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.avercare.core.base.BaseFragment
import com.example.avercare.databinding.FragmentProfileManagementBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileManagementFragment : BaseFragment<FragmentProfileManagementBinding>() {

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentProfileManagementBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        setClick()
    }

    private fun initUi() {
    }

    private fun setClick() {

    }

}