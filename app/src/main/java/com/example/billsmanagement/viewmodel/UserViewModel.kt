package com.example.billsmanagement.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.billsmanagement.model.LoginRequest
import com.example.billsmanagement.model.LoginResponse
import com.example.billsmanagement.model.RegisterRequest
import com.example.billsmanagement.model.RegisterResponse
import com.example.billsmanagement.repository.UserRepository
import com.example.billsmanagement.ui.Login
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    val userRepository = UserRepository()
    val regLiveData = MutableLiveData<RegisterResponse>()
    val errorLiveData = MutableLiveData<String>()

    fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            val response=userRepository.registerUser(registerRequest)
            if (response.isSuccessful){
                regLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

            }
//live data holds data
//launch is responsible for launching/creating the coroutine
//viewmodel by default has its own view model scope we launch it because to cancel.
