package com.example.cleanarchitecture.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cleanarchitecture.core.base.BaseFragment
import com.example.cleanarchitecture.data.remote.Resource
import com.example.cleanarchitecture.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>() {

    private val viewModel: AuthViewModel by viewModels()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSignupBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnSubmit.setOnClickListener {
            if (binding.etEmail.text?.isEmpty() == true){
                Toast.makeText(requireContext(), "Please enter email",Toast.LENGTH_LONG).show()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.authState.collectLatest { state ->
                when (state) {
                    is Resource.Idle -> {
                        hideLoading()
                    }
                    is Resource.Loading -> {
                       showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        findNavController().popBackStack()
                    }
                    is Resource.Error -> {
                        hideLoading()
                        showError(state.message)
                    }
                }
            }
        }
    }
}
