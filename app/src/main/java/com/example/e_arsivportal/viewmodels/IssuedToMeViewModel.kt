package com.example.e_arsivportal.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_arsivportal.models.CustomerModel
import com.example.e_arsivportal.models.InvoiceModel
import com.example.e_arsivportal.service.Api
import com.example.e_arsivportal.service.ApiService
import com.example.e_arsivportal.service.Database
import kotlinx.coroutines.*

class IssuedToMeViewModel: ViewModel() {

    private var job : Job? = null
    private val apiService = ApiService()

    val liveData = MutableLiveData<List<InvoiceModel>>()


    fun getAllIssuedToMe(context: Context) {

        val handler = CoroutineExceptionHandler {context, throwable ->
            //Toast.makeText(Context,"Bir sorun meydana geldi",Toast.LENGTH_SHORT).show()
        }

        job = CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = apiService.getAllIssuedToMe()

            withContext(Dispatchers.Main){
                if(response.isSuccessful) {
                    response.body()?.let {

                        liveData.value = it

                    }
                }
            }

        }
    }
}