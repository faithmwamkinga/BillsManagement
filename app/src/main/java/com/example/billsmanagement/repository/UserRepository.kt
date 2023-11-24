package com.example.billsmanagement.repository

import com.example.billsmanagement.api.ApiClient
import com.example.billsmanagement.api.ApiInterface
import com.example.billsmanagement.model.LoginRequest
import com.example.billsmanagement.model.LoginResponse
import com.example.billsmanagement.model.RegisterRequest
import com.example.billsmanagement.model.RegisterResponse
import com.example.billsmanagement.ui.Login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient=ApiClient.buildClient(ApiInterface::class.java)
    suspend fun registerUser(registerRequest: RegisterRequest):Response<RegisterResponse>{
        return withContext(Dispatchers.IO){
            apiClient.registerUser(registerRequest)
        }
    }

}