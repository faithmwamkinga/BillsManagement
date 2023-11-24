package com.example.billsmanagement.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.billsmanagement.model.LoginRequest
import com.example.billsmanagement.model.LoginResponse
import com.example.billsmanagement.model.RegisterRequest
import com.example.billsmanagement.model.RegisterResponse
import com.example.billsmanagement.repository.LoginRepository
import com.example.billsmanagement.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel(){
        val loginRepository = LoginRepository()
        val errorLiveData = MutableLiveData<String>()
        val logLiveData= MutableLiveData<LoginResponse>()

        fun loginUser(loginRequest: LoginRequest) {
            viewModelScope.launch {
                val response=loginRepository.loginUser(loginRequest)
                if (response.isSuccessful){
                    logLiveData.postValue(response.body())
                }
                else{
                    errorLiveData.postValue(response.errorBody()?.string())
                }
            }
        }
    }