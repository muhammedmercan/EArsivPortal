package com.example.e_arsivportal.presentation.customers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_arsivportal.domain.model.CustomerModel
import com.example.e_arsivportal.domain.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
@HiltViewModel

class AddCustomerViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    val liveData = MutableLiveData<Long>()
    val liveDataUpdate = MutableLiveData<Int>()


    fun updateDataInRoom(customer: CustomerModel) {

        viewModelScope.launch {

            val returnValue = repository.updateCustomer(customer)
            liveDataUpdate.postValue(returnValue)

        }
    }

    fun saveDataToRoom(customer: CustomerModel) {

        viewModelScope.launch {

            val returnValue = repository.addCustomer(customer)
            liveData.postValue(returnValue)

        }
    }
}