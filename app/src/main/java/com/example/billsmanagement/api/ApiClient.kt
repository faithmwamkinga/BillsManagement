package com.example.billsmanagement.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val gson= GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    var retrofit = Retrofit.Builder()
        .baseUrl("http://13.37.106.218")
        .addConverterFactory(GsonConverterFactory.create(gson)) //converts json to kotlin
        .build()
    fun <T> buildClient(apiInterface:Class<T>):T{
        return retrofit.create(apiInterface)
    }

}
