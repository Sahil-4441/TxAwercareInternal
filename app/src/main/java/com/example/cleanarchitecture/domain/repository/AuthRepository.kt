package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(email: String, password: String): Flow<User>
    fun signup(name: String, email: String, password: String): Flow<User>
    suspend fun saveUser(user: User)
    fun getUser(): Flow<User?>
    suspend fun logout()
}
