package com.example.avercare.data.remote

import com.example.avercare.domain.model.Todo
import com.example.avercare.domain.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


data class LoginRequest(val email: String, val password: String)
data class SignupRequest(val name: String, val email: String, val password: String)

interface ApiService {
    @GET("todos")
    suspend fun getTodos(): List<Todo>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): User

    @POST("auth/signup")
    suspend fun signup(@Body request: SignupRequest): User
}
