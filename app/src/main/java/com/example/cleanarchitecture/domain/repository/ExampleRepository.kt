package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface ExampleRepository {
    fun getTodos(): Flow<List<Todo>>
}
