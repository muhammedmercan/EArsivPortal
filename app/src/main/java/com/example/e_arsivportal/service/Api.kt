package com.example.e_arsivportal.service

import com.example.e_arsivportal.models.*
import com.google.gson.Gson
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("getAllIssuedToMe.php")
    suspend fun getAllIssuedToMe(@Body requestModel: GetInvoicesModel): Response<List<IncomingInvoiceModel>>

    @POST("getAllOutgoingInvoices.php")
    suspend fun getAllOutgoingInvoices(@Body requestModel: GetInvoicesModel): Response<List<OutgoingInvoiceModel>>

    @POST("getHtml.php")
    suspend fun getHtml(@Body requestModel: CommonStringModel): Response<String>

    @POST("getRecipientData.php")
    suspend fun getRecipientData(@Body requestModel: CommonStringModel): Response<RecipientDataModel>

    @POST("createInvoice.php")
    suspend fun createInvoice(@Body requestModel: CreateInvoiceModel): Response<String>

    @POST("login.php")
    suspend fun login(@Body requestModel: LoginModel): Response<String>

    @POST("get_document.php")
    suspend fun getDocument(@Body requestModel: CommonStringModel): Response<DocumentModel>
}