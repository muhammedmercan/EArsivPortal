package com.example.e_arsivportal.service

import com.example.biochakraastralterapi.services.ServiceBuilder
import com.example.e_arsivportal.models.InvoiceModel
import retrofit2.Response

class ApiService {

    suspend fun getAllIssuedToMe() : Response<List<InvoiceModel>> {
        val retrofit = ServiceBuilder.buildService(Api::class.java)
        return retrofit.getAllIssuedToMe()
    }

}