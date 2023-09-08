package com.example.e_arsivportal.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import com.example.e_arsivportal.domain.repository.RepositoryInterface
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
import com.example.e_arsivportal.data.remote.Api
import com.example.e_arsivportal.data.local.roomdb.Dao
import com.example.e_arsivportal.util.Resource
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(

    private val dao: Dao,
    private val retrofitApi: Api,
    private val preferences: SharedPreferences) : RepositoryInterface {
    override suspend fun getAllIssuedToMe(requestModel: GetInvoicesModel): Resource<List<IncomingInvoiceModel>> {
        return retrofitApi.getAllIssuedToMe(requestModel)
    }

    override suspend fun getAllOutgoingInvoices(requestModel: GetInvoicesModel): Resource<List<OutgoingInvoiceModel>> {
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