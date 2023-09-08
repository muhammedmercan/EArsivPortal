package com.example.e_arsivportal.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import retrofit2.Response

class FakeRepository : RepositoryInterface {
    private val incomingInvoices: MutableList<IncomingInvoiceModel> = mutableListOf()
    private val outgoingInvoices: MutableList<OutgoingInvoiceModel> = mutableListOf()
    private val htmlResponse: String = ""
    private val recipientData: RecipientDataModel? = null
    private val documentData: DocumentModel? = null
    private val products: MutableList<ProductModel> = mutableListOf()
    private val customers: MutableList<CustomerModel> = mutableListOf()
    private var savedUsername: String? = null
    private var savedPassword: String? = null

    override suspend fun getAllIssuedToMe(requestModel: GetInvoicesModel): Response<List<IncomingInvoiceModel>> {
        // Sahte gelen faturaları burada oluşturabilirsiniz
        return Response.success(incomingInvoices)
    }

    override suspend fun getAllOutgoingInvoices(requestModel: GetInvoicesModel): Response<List<OutgoingInvoiceModel>> {
        // Sahte çıkan faturaları burada oluşturabilirsiniz
        return Response.success(outgoingInvoices)
    }

    override suspend fun getHtml(requestModel: CommonStringModel): Response<String> {
        // Sahte HTML yanıtını burada oluşturabilirsiniz
        return Response.success(htmlResponse)
    }

    override suspend fun getRecipientData(requestModel: CommonStringModel): Response<RecipientDataModel> {
        // Sahte alıcı verilerini burada oluşturabilirsiniz
        return Response.success(recipientData)
    }

    override suspend fun createInvoice(requestModel: CreateInvoiceModel): Response<String> {
        // Sahte bir cevap dönebilirsiniz, örneğin işlem başarılı ise "Success" gibi
        return Response.success("Success")
    }

    override suspend fun login(requestModel: LoginModel): Response<String> {
        // Sahte bir cevap dönebilirsiniz, örneğin giriş başarılı ise "Success" gibi
        return Response.success("Success")
    }

    override suspend fun getDocument(requestModel: CommonStringModel): Response<DocumentModel> {
        // Sahte belge verilerini burada oluşturabilirsiniz
        return Response.success(documentData)
    }

    override suspend fun addProduct(product: ProductModel): Long {
        products.add(ProductModel("makina","adet",500f,20))

        // Sahte bir ürün eklemesi işlemi yapabilirsiniz
        return 1L // Örnek olarak eklenen ürünün ID'sini döndürüyoruz
    }

    override suspend fun updateProduct(product: ProductModel): Int {
        // Sahte bir ürün güncelleme işlemi yapabilirsiniz
        return 1 // Örnek olarak güncellenen ürün sayısını döndürüyoruz
    }

    override fun getAllProducts(): LiveData<MutableList<ProductModel>> {
        // Sahte ürün verilerini burada oluşturabilirsiniz
        return MutableLiveData(products)
    }

    override suspend fun deleteProduct(id: Int) {
        // Sahte bir ürün silme işlemi yapabilirsiniz
        products.find {
            it.id == id
        }
    }

    override suspend fun addCustomer(customer: CustomerModel): Long {
        // Sahte bir müşteri eklemesi işlemi yapabilirsiniz
        return 1L // Örnek olarak eklenen müşterinin ID'sini döndürüyoruz
    }

    override suspend fun updateCustomer(customer: CustomerModel): Int {
        // Sahte bir müşteri güncelleme işlemi yapabilirsiniz
        return 1 // Örnek olarak güncellenen müşteri sayısını döndürüyoruz
    }

    override fun getAllCustomers(): LiveData<MutableList<CustomerModel>> {
        // Sahte müşteri verilerini burada oluşturabilirsiniz
        return MutableLiveData(customers)
    }

    override suspend fun deleteCustomer(id: Int) {
        TODO("Not yet implemented")
    }

    override fun saveUser(username: String, password: String) {
        // Sahte kullanıcı bilgilerini saklama işlemi
        savedUsername = username
        savedPassword = password
    }

    override fun deleteUser() {
        // Sahte kullanıcı bilgilerini silme işlemi
        savedUsername = null
        savedPassword = null
    }

    override fun getUsername(): String? {
        // Sahte kullanıcı adını döndürebilirsiniz
        return savedUsername
    }

    override fun getPassword(): String? {
        // Sahte parolayı döndürebilirsiniz
        return savedPassword
    }
}