package com.example.e_arsivportal.service

import com.example.e_arsivportal.models.InvoiceModel
import retrofit2.http.GET

interface Api {

    @GET("getAllIssuedToMe.php")
    suspend fun getAllIssuedToMe(): retrofit2.Response<List<InvoiceModel>>

}