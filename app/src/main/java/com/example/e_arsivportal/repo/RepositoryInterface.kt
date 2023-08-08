package com.example.e_arsivportal.repo

import com.example.e_arsivportal.models.CommonStringModel
import com.example.e_arsivportal.models.CreateInvoiceModel
import com.example.e_arsivportal.models.DocumentModel
import com.example.e_arsivportal.models.GetInvoicesModel
import com.example.e_arsivportal.models.IncomingInvoiceModel
import com.example.e_arsivportal.models.LoginModel
import com.example.e_arsivportal.models.OutgoingInvoiceModel
import com.example.e_arsivportal.models.RecipientDataModel
import retrofit2.Response

interface RepositoryInterface {

    suspend fun getAllIssuedToMe(requestModel: GetInvoicesModel): Response<List<IncomingInvoiceModel>>

    suspend fun getAllOutgoingInvoices(requestModel: GetInvoicesModel): Response<List<OutgoingInvoiceModel>>

    suspend fun getHtml(requestModel: CommonStringModel): Response<String>

    suspend fun getRecipientData(requestModel: CommonStringModel): Response<RecipientDataModel>

    suspend fun createInvoice(requestModel: CreateInvoiceModel): Response<String>

    suspend fun login(requestModel: LoginModel): Response<String>

    suspend fun getDocument(requestModel: CommonStringModel): Response<DocumentModel>


}