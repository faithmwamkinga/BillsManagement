package com.example.billsmanagement.repository

import com.example.billsmanagement.api.ApiClient
import com.example.billsmanagement.api.ApiInterface
import com.example.billsmanagement.model.LoginRequest
import com.example.billsmanagement.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRepository {
    val apiClient= ApiClient.buildClient(ApiInterface::class.java)
    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse> {
        return withContext(Dispatchers.IO){
            apiClient.loginUser(loginRequest)
        }
    }

}
