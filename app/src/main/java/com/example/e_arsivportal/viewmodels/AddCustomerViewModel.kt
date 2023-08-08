package com.example.e_arsivportal.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_arsivportal.models.CustomerModel
import com.example.e_arsivportal.service.Database
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*

class AddCustomerViewModel(): ViewModel() {


    private var job : Job? = null
    val liveData = MutableLiveData<Long>()
    val liveDataUpdate = MutableLiveData<Int>()


    fun updateDataInRoom(customer: CustomerModel, context: Context) {

        job = CoroutineScope(Dispatchers.IO).launch {
            val returnValue = Database(context).dao().updateCustomer(customer)

            liveDataUpdate.postValue(returnValue)

        }

    }

    fun saveDataToRoom(customer: CustomerModel, context: Context) {

        job = CoroutineScope(Dispatchers.IO).launch {
            val returnValue = Database(context).dao().addCustomer(customer)

            liveData.postValue(returnValue)

        }
    }




}