package com.example.e_arsivportal.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_arsivportal.models.CommonStringModel
import com.example.e_arsivportal.models.GetInvoicesModel
import com.example.e_arsivportal.models.IncomingInvoiceModel
import com.example.e_arsivportal.models.OutgoingInvoiceModel
import com.example.e_arsivportal.service.ApiService
import kotlinx.coroutines.*

class OutgoingInvoicesViewModel: ViewModel() {

    private var job : Job? = null
    private val apiService = ApiService()

    val invoicesliveData = MutableLiveData<List<OutgoingInvoiceModel>>()
    val htmlLiveData = MutableLiveData<String>()


    fun getOutgoingInvoices(context: Context, startDate:String, endDate:String) {

        val handler = CoroutineExceptionHandler {context, throwable ->
            //Toast.makeText(Context,"Bir sorun meydana geldi",Toast.LENGTH_SHORT).show()
        }

        job = CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = apiService.getAllOutgoingInvoices(GetInvoicesModel(startDate,endDate))

            withContext(Dispatchers.Main){
                if(response.isSuccessful) {
                    response.body()?.let {

                        invoicesliveData.value = it

                    }
                }
            }

        }
    }

    fun getHtml(context: Context, ettn : String) {

        val handler = CoroutineExceptionHandler {context, throwable ->
            //Toast.makeText(Context,"Bir sorun meydana geldi",Toast.LENGTH_SHORT).show()
        }

        job = CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = apiService.getHtml(CommonStringModel(ettn))

            withContext(Dispatchers.Main){
                if(response.isSuccessful) {
                    response.body()?.let {

                        htmlLiveData.value = it

                    }
                }
            }

        }


    }
}