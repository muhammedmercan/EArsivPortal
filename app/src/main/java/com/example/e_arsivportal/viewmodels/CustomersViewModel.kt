package com.example.e_arsivportal.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_arsivportal.models.CustomerModel
import com.example.e_arsivportal.repo.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CustomersViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    val liveData = MutableLiveData<MutableList<CustomerModel>>()


    fun getDataFromRoom() {

        viewModelScope.launch {

            val customers = repository.getAllCustomers()

            liveData.postValue(customers)

            //liveData.value = produtcs
        }

    }

    fun deleteCustomer(id: Int) {

        viewModelScope.launch {

            repository.deleteCustomer(id)

        }
    }
}