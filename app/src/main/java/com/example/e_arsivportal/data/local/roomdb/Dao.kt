package com.example.e_arsivportal.data.local.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.e_arsivportal.domain.model.CustomerModel
import com.example.e_arsivportal.domain.model.ProductModel

@androidx.room.Dao
interface Dao {


    @Insert
    suspend fun addProduct(product: ProductModel): Long

    @Update
    suspend fun updateProduct(product: ProductModel): Int

    @Query("SELECT * FROM products")
    fun getAllProducts() : LiveData<MutableList<ProductModel>>

    @Query("DELETE FROM products WHERE id = :id")
    suspend fun deleteProduct(id:Int)

    @Insert
    suspend fun addCustomer(product: CustomerModel): Long

    @Update
    suspend fun updateCustomer(product: CustomerModel): Int

    @Query("SELECT * FROM customers")
    fun getAllCustomers() : LiveData<MutableList<CustomerModel>>

    @Query("DELETE FROM customers WHERE id = :id")
    suspend fun deleteCustomer(id:Int)


}