package com.example.billsmanagement.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
@Expose var message:String,
@Expose @SerializedName("user_id")var user_id:String,
@Expose @SerializedName("access_token")var access_token:String

)