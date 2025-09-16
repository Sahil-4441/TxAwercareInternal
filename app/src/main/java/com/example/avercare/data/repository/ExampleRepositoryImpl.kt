package com.example.avercare.data.repository

import com.example.avercare.data.remote.ApiService
import com.example.avercare.domain.model.Todo
import com.example.avercare.domain.repository.ExampleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExampleRepositoryImpl @Inject constructor(
    private val api: ApiService
) : ExampleRepository {

    override fun getTodos(): Flow<List<Todo>> = flow {
        val todos = api.getTodos()
        emit(todos)
    }.flowOn(Dispatchers.IO)
}
