package com.example.avercare.data.repository

import com.example.avercare.data.local.DataStoreManager
import com.example.avercare.data.remote.ApiService
import com.example.avercare.domain.model.User
import com.example.avercare.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dataStore: DataStoreManager
) : AuthRepository {

    override fun login(email: String, password: String): Flow<User> = flow {
        val user = User("1", email, "Test User", "fake_token")
        emit(user)
        saveUser(user)
    }.flowOn(Dispatchers.IO)

    override fun signup(name: String, email: String, password: String): Flow<User> = flow {
        val user = User("1", email, name, "fake_token")
        emit(user)
        saveUser(user)
    }.flowOn(Dispatchers.IO)


    override suspend fun saveUser(user: User) {
        dataStore.saveUser(user.id)
    }

    override fun getUser(): Flow<User?> = dataStore.getUser()

    override suspend fun logout() {
        dataStore.clearUser()
    }
}
