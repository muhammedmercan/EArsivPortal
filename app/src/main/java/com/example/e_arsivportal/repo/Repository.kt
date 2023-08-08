package com.example.e_arsivportal.repo

import com.example.e_arsivportal.models.CommonStringModel
import com.example.e_arsivportal.models.CreateInvoiceModel
import com.example.e_arsivportal.models.DocumentModel
import com.example.e_arsivportal.models.GetInvoicesModel
import com.example.e_arsivportal.models.IncomingInvoiceModel
import com.example.e_arsivportal.models.LoginModel
import com.example.e_arsivportal.models.OutgoingInvoiceModel
import com.example.e_arsivportal.models.RecipientDataModel
import com.example.e_arsivportal.service.Api
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(

        private val retrofitApi: Api ) : RepositoryInterface {
    override suspend fun getAllIssuedToMe(requestModel: GetInvoicesModel): Response<List<IncomingInvoiceModel>> {
        return retrofitApi.getAllIssuedToMe(requestModel)
    }

    override suspend fun getAllOutgoingInvoices(requestModel: GetInvoicesModel): Response<List<OutgoingInvoiceModel>> {
        return retrofitApi.getAllOutgoingInvoices(requestModel)
    }

    override suspend fun getHtml(requestModel: CommonStringModel): Response<String> {
        return retrofitApi.getHtml(requestModel)
    }

    override suspend fun getRecipientData(requestModel: CommonStringModel): Response<RecipientDataModel> {
        return retrofitApi.getRecipientData(requestModel)
    }

    override suspend fun createInvoice(requestModel: CreateInvoiceModel): Response<String> {
        return retrofitApi.createInvoice(requestModel)
    }

    override suspend fun login(requestModel: LoginModel): Response<String> {
        return retrofitApi.login(requestModel)
    }

    override suspend fun getDocument(requestModel: CommonStringModel): Response<DocumentModel> {
        return retrofitApi.getDocument(requestModel)
    }
}