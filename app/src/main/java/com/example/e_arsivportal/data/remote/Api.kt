package com.example.e_arsivportal.data.remote

import com.example.e_arsivportal.domain.model.CommonStringModel
import com.example.e_arsivportal.domain.model.CreateInvoiceModel
import com.example.e_arsivportal.domain.model.DocumentModel
import com.example.e_arsivportal.domain.model.GetInvoicesModel
import com.example.e_arsivportal.domain.model.IncomingInvoiceModel
import com.example.e_arsivportal.domain.model.LoginModel
import com.example.e_arsivportal.domain.model.OutgoingInvoiceModel
import com.example.e_arsivportal.domain.model.RecipientDataModel
import com.example.e_arsivportal.util.Resource
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("getAllIssuedToMe.php")
    suspend fun getAllIssuedToMe(@Body requestModel: GetInvoicesModel): Resource<List<IncomingInvoiceModel>>

    @POST("getAllOutgoingInvoices.php")
    suspend fun getAllOutgoingInvoices(@Body requestModel: GetInvoicesModel): Resource<List<OutgoingInvoiceModel>>

    @POST("getHtml.php")
    suspend fun getHtml(@Body requestModel: CommonStringModel): Response<String>

    @POST("getRecipientData.php")
    suspend fun getRecipientData(@Body requestModel: CommonStringModel): Response<RecipientDataModel>

    @POST("createInvoiceEmpty.php")
    suspend fun createInvoice(@Body requestModel: CreateInvoiceModel): Response<String>

    @POST("login.php")
    suspend fun login(@Body requestModel: LoginModel): Response<String>

    @POST("get_document.php")
    suspend fun getDocument(@Body requestModel: CommonStringModel): Response<DocumentModel>
}