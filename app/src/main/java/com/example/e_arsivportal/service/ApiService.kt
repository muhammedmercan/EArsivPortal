package com.example.e_arsivportal.service

import com.example.biochakraastralterapi.services.ServiceBuilder
import com.example.e_arsivportal.models.CommonStringModel
import com.example.e_arsivportal.models.GetInvoicesModel
import com.example.e_arsivportal.models.IncomingInvoiceModel
import com.example.e_arsivportal.models.OutgoingInvoiceModel
import retrofit2.Response
import retrofit2.http.Body

class ApiService {

    suspend fun getAllIssuedToMe(@Body requestModel: GetInvoicesModel) : Response<List<IncomingInvoiceModel>> {
        val retrofit = ServiceBuilder.buildService(Api::class.java)
        return retrofit.getAllIssuedToMe(requestModel)
    }

    suspend fun getAllOutgoingInvoices(@Body requestModel: GetInvoicesModel) : Response<List<OutgoingInvoiceModel>> {
        val retrofit = ServiceBuilder.buildService(Api::class.java)
        return retrofit.getAllOutgoingInvoices(requestModel)
    }

    suspend fun getHtml(@Body requestModel: CommonStringModel) : Response<String> {
        val retrofit = ServiceBuilder.buildService(Api::class.java)
        return retrofit.getHtml(requestModel)
    }

}