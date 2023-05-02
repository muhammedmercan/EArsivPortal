package com.example.e_arsivportal.service

import com.example.e_arsivportal.models.CommonStringModel
import com.example.e_arsivportal.models.GetInvoicesModel
import com.example.e_arsivportal.models.IncomingInvoiceModel
import com.example.e_arsivportal.models.OutgoingInvoiceModel
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("getAllIssuedToMe.php")
    suspend fun getAllIssuedToMe(@Body userRequest: GetInvoicesModel): retrofit2.Response<List<IncomingInvoiceModel>>

    @POST("getAllOutgoingInvoices.php")
    suspend fun getAllOutgoingInvoices(@Body userRequest: GetInvoicesModel): retrofit2.Response<List<OutgoingInvoiceModel>>

    @POST("getHtml.php")
    suspend fun getHtml(@Body userRequest: CommonStringModel): retrofit2.Response<String>


}