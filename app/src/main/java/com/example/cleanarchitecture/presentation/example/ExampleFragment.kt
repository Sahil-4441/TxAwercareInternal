package com.example.cleanarchitecture.presentation.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchitecture.core.base.BaseFragment
import com.example.cleanarchitecture.data.remote.Resource
import com.example.cleanarchitecture.databinding.FragmentExampleBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExampleFragment : BaseFragment<FragmentExampleBinding>() {

    private val viewModel: ExampleViewModel by viewModels()
    private val adapter = ExampleAdapter()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentExampleBinding =
        FragmentExampleBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                when (state) {
                    is Resource.Idle -> {
                        hideLoading()
                    }
                    is Resource.Loading -> {
                      showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        adapter.submitList(state.data)
                    }
                    is Resource.Error -> {
                       hideLoading()
                        showError(state.message)
                    }
                }
            }
        }

        viewModel.loadTodos()
    }
}
