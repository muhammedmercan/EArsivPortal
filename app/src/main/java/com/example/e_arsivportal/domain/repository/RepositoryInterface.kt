package com.example.e_arsivportal.domain.repository

import androidx.lifecycle.LiveData
import com.example.e_arsivportal.domain.model.CommonStringModel
import com.example.e_arsivportal.domain.model.CreateInvoiceModel
import com.example.e_arsivportal.domain.model.CustomerModel
import com.example.e_arsivportal.domain.model.DocumentModel
import com.example.e_arsivportal.domain.model.GetInvoicesModel
import com.example.e_arsivportal.domain.model.IncomingInvoiceModel
import com.example.e_arsivportal.domain.model.LoginModel
import com.example.e_arsivportal.domain.model.OutgoingInvoiceModel
import com.example.e_arsivportal.domain.model.ProductModel
import com.example.e_arsivportal.domain.model.RecipientDataModel
import com.example.e_arsivportal.util.Resource
import retrofit2.Response

interface RepositoryInterface {

    suspend fun getAllIssuedToMe(requestModel: GetInvoicesModel): Resource<List<IncomingInvoiceModel>>

    suspend fun getAllOutgoingInvoices(requestModel: GetInvoicesModel): Resource<List<OutgoingInvoiceModel>>

    suspend fun getHtml(requestModel: CommonStringModel): Response<String>

    suspend fun getRecipientData(requestModel: CommonStringModel): Response<RecipientDataModel>

    suspend fun createInvoice(requestModel: CreateInvoiceModel): Response<String>

    suspend fun login(requestModel: LoginModel): Response<String>

    suspend fun getDocument(requestModel: CommonStringModel): Response<DocumentModel>

    suspend fun addProduct(product: ProductModel): Long

    suspend fun updateProduct(product: ProductModel): Int

    fun getAllProducts() : LiveData<MutableList<ProductModel>>

    suspend fun deleteProduct(id:Int)

    suspend fun addCustomer(product: CustomerModel): Long

    suspend fun updateCustomer(product: CustomerModel): Int

    fun getAllCustomers() : LiveData<MutableList<CustomerModel>>

    suspend fun deleteCustomer(id:Int)

    fun saveUser(username:String, password:String)

    fun deleteUser()

    fun getUsername() : String?

    fun getPassword() : String?

}