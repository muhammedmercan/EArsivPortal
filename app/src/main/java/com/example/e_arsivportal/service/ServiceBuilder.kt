package com.example.biochakraastralterapi.services

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object  ServiceBuilder {


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://mercanmakina.net/earsiv-fatura/examples/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}