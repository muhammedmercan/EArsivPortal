package com.example.e_arsivportal.repo

import androidx.lifecycle.LiveData
import com.example.e_arsivportal.models.CommonStringModel
import com.example.e_arsivportal.models.CreateInvoiceModel
import com.example.e_arsivportal.models.CustomerModel
import com.example.e_arsivportal.models.DocumentModel
import com.example.e_arsivportal.models.GetInvoicesModel
import com.example.e_arsivportal.models.IncomingInvoiceModel
import com.example.e_arsivportal.models.LoginModel
import com.example.e_arsivportal.models.OutgoingInvoiceModel
import com.example.e_arsivportal.models.ProductModel
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

    suspend fun addProduct(product: ProductModel): Long

    suspend fun updateProduct(product: ProductModel): Int

    suspend fun getAllProducts() : MutableList<ProductModel>

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