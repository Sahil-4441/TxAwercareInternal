package com.example.avercare.domain.repository

import com.example.avercare.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface ExampleRepository {
    fun getTodos(): Flow<List<Todo>>
}
