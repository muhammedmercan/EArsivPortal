package com.example.e_arsivportal.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_arsivportal.models.*
import com.example.e_arsivportal.repo.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
@HiltViewModel
class OutgoingInvoicesViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    val invoicesliveData = MutableLiveData<List<OutgoingInvoiceModel>>()
    val htmlLiveData = MutableLiveData<String>()
    val documentLiveData = MutableLiveData<DocumentModel>()

    fun getDocument(ettn: String) {

        val handler = CoroutineExceptionHandler {context, throwable ->
            //Toast.makeText(Context,"Bir sorun meydana geldi",Toast.LENGTH_SHORT).show()
            println("sadasdas")
            println(throwable.toString() + "asdsad")
        }

        viewModelScope.launch(handler) {
            val response = repository.getDocument(CommonStringModel(ettn))

            if(response.isSuccessful) {
                response.body()?.let {
                    documentLiveData.value = it
                }
            }
        }
    }

    fun getOutgoingInvoices(startDate:String, endDate:String) {

        val handler = CoroutineExceptionHandler {context, throwable ->
            //Toast.makeText(Context,"Bir sorun meydana geldi",Toast.LENGTH_SHORT).show()
        }

        viewModelScope.launch(handler) {
            val response = repository.getAllOutgoingInvoices(GetInvoicesModel(startDate,endDate))

            if(response.isSuccessful) {
                response.body()?.let {
                    invoicesliveData.value = it

                    }
                }
            }
        }


    fun getHtml(context: Context, ettn : String) {

        val handler = CoroutineExceptionHandler {context, throwable ->
            //Toast.makeText(Context,"Bir sorun meydana geldi",Toast.LENGTH_SHORT).show()
        }

        viewModelScope.launch(handler) {
            val response = repository.getHtml(CommonStringModel(ettn))

            if(response.isSuccessful) {
                response.body()?.let {
                    htmlLiveData.value = it
                }
            }
        }
    }
}