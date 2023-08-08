package com.example.e_arsivportal.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_arsivportal.models.CustomerModel
import com.example.e_arsivportal.repo.RepositoryInterface
import com.example.e_arsivportal.service.Database
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CustomersViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    private var job : Job? = null
    val liveData = MutableLiveData<MutableList<CustomerModel>>()


    fun getDataFromRoom(context: Context) {

        job = CoroutineScope(Dispatchers.IO).launch {
            val customers = Database(context).dao().getAllCustomers()

            liveData.postValue(customers)

            //liveData.value = produtcs

        }
    }

    fun deleteCustomer(id: Int, context: Context) {

        job = CoroutineScope(Dispatchers.IO).launch {
            Database(context).dao().deleteCustomer(id)



        }

    }

}