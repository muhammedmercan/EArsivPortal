package com.example.e_arsivportal.repo

import android.content.SharedPreferences
import androidx.core.content.edit
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
import com.example.e_arsivportal.service.Api
import com.example.e_arsivportal.roomdb.Dao
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(

    private val dao: Dao,
    private val retrofitApi: Api,
    private val preferences: SharedPreferences) : RepositoryInterface {
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

    override suspend fun addProduct(product: ProductModel): Long {
        return dao.addProduct(product)
    }

    override suspend fun updateProduct(product: ProductModel): Int {
        return dao.updateProduct(product)
    }

    override fun getAllProducts(): LiveData<MutableList<ProductModel>> {
        return dao.getAllProducts()
    }

    override suspend fun deleteProduct(id: Int) {
        return dao.deleteProduct(id)
    }

    override suspend fun addCustomer(product: CustomerModel): Long {
        return dao.addCustomer(product)
    }

    override suspend fun updateCustomer(product: CustomerModel): Int {
        return dao.updateCustomer(product)
    }

    override fun getAllCustomers(): LiveData<MutableList<CustomerModel>> {
        return dao.getAllCustomers()
    }

    override suspend fun deleteCustomer(id: Int) {
        return dao.deleteCustomer(id)
    }

    override fun saveUser(username:String, password:String) {

        preferences.edit(commit = true) {
            putString("username",username)
            putString("password",password)
        }
    }

    override fun deleteUser() {
        return preferences.edit().clear().apply()
    }


    override fun getUsername() : String? {
        return preferences.getString("username","")
    }

    override fun getPassword() : String? {
        return preferences.getString("password","")
    }



}