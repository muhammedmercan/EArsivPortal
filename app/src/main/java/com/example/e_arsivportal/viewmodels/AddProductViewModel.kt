package com.example.e_arsivportal.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_arsivportal.models.ProductModel
import com.example.e_arsivportal.service.Database
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*

class AddProductViewModel(): ViewModel() {


    private var job : Job? = null
    val liveData = MutableLiveData<Long>()
    val liveDataUpdate = MutableLiveData<Int>()


    fun updateDataInRoom(product: ProductModel, context: Context) {

        job = CoroutineScope(Dispatchers.IO).launch {
            val returnValue = Database(context).dao().updateProduct(product)

            liveDataUpdate.postValue(returnValue)

        }

    }

    fun saveDataToRoom(product: ProductModel, context: Context) {

        job = CoroutineScope(Dispatchers.IO).launch {
            val returnValue = Database(context).dao().addProduct(product)

            liveData.postValue(returnValue)

        }
    }




}