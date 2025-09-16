package com.example.avercare.presentation.example

import androidx.lifecycle.viewModelScope
import com.example.avercare.core.base.BaseViewModel
import com.example.avercare.domain.model.Todo
import com.example.avercare.domain.repository.ExampleRepository
import com.example.avercare.data.remote.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val exampleRepository: ExampleRepository
) : BaseViewModel() {

    private val _state = MutableStateFlow<Resource<List<Todo>>>(Resource.Idle)
    val state: StateFlow<Resource<List<Todo>>> = _state

    fun loadTodos() {
        viewModelScope.launch {
            exampleRepository.getTodos()
                .onStart { _state.value = Resource.Loading }
                .catch { e ->
                    _state.value = Resource.Error(e.message ?: "Unknown error") }
                .collect { list ->
                    _state.value = Resource.Success(list)
                }
        }
    }
}

