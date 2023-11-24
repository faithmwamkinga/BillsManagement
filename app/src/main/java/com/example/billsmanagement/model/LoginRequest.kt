package com.example.billsmanagement.model

import com.google.gson.annotations.Expose

data class LoginRequest(
    @Expose var email:String,
    @Expose var password:String
)


