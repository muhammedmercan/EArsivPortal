package com.example.e_arsivportal.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.e_arsivportal.getOrAwaitValue
import com.example.e_arsivportal.models.CustomerModel
import com.example.e_arsivportal.models.ProductModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
@HiltAndroidTest
class DaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    lateinit var database: Database

    private lateinit var dao : Dao


    @Before
    fun setup() {

        hiltRule.inject()
        dao = database.dao()

    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun addProduct() = runBlocking {

        val exampleProduct = ProductModel("Tablet","2",22.5f,18)
        dao.addProduct(exampleProduct)

        val list = dao.getAllProducts().getOrAwaitValue()
        assertThat(list).contains(exampleProduct)

    }

    @Test
    fun updateProduct() = runBlocking {

        val exampleProduct = ProductModel("Tablet","2",22.5f,18)
        dao.addProduct(exampleProduct)

        val exampleProduct2 = ProductModel("Telefon","2",22.5f,18)
        dao.updateProduct(exampleProduct2)
        val data = dao.getAllProducts().getOrAwaitValue().get(0)

        assertThat(data.name).isEqualTo("Telefon")

    }

    @Test
    fun getAllProducts() = runBlocking {


        dao.addProduct(ProductModel("Tablet","1",12312.5f,20))
        dao.addProduct(ProductModel("Telefon","4",2341.5f,20))
        dao.addProduct(ProductModel("Bilgisyar","5",5123.5f,20))

        val data = dao.getAllProducts().getOrAwaitValue()

        assertThat(data.size).isEqualTo(3)

    }

    @Test
    fun deleteProduct() = runBlocking {

        val exampleProduct = ProductModel("Tablet","2",22.5f,18)
        dao.addProduct(exampleProduct)

        dao.deleteProduct(1)
        val data = dao.getAllProducts().getOrAwaitValue()

        assertThat(data).contains(exampleProduct)

    }

    @Test
    fun addCustomer() = runBlocking {

        val exampleCustomer = CustomerModel("24473463216","eh","Muhammed","Mercan","Halkalı","adres")
        dao.addCustomer(exampleCustomer)

        val list = dao.getAllCustomers().getOrAwaitValue()
        assertThat(list).contains(exampleCustomer)

    }

    @Test
    fun updateCustomer() = runTest {

        val exampleCustomer = CustomerModel("24473463216","eh","Muhammed","Mercan","Halkalı","adres")
        dao.addCustomer(exampleCustomer)

        val exampleCustomer2 = CustomerModel("24473463216","eh","Cansu","Mercan","Halkalı","adres")
        dao.updateCustomer(exampleCustomer2)

        val data = dao.getAllProducts().getOrAwaitValue().get(0)

        assertThat(data.name).isEqualTo("Cansu")

    }

    @Test
    fun getAllCustomers() = runBlocking {

        dao.addCustomer(CustomerModel("11111111111","eh","Muhammed","Mercan","Halkalı","adres"))
        dao.addCustomer(CustomerModel("22222222222","eh","Muhammed","Mercan","Halkalı","adres"))
        dao.addCustomer(CustomerModel("33333333333","eh","Muhammed","Mercan","Halkalı","adres"))


        val data = dao.getAllCustomers().getOrAwaitValue()

        assertThat(data.size).isEqualTo(3)

    }

    @Test
    fun deleteCustomer() = runBlocking {

        val exampleCustomer = CustomerModel("11111111111","eh","Muhammed","Mercan","Halkalı","adres")
        dao.addCustomer(exampleCustomer)

        dao.deleteCustomer(1)
        val data = dao.getAllCustomers().getOrAwaitValue()

        assertThat(data).contains(exampleCustomer)

    }

    //productModel

}