package com.example.e_arsivportal.presentation.outgoinginvoices

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_arsivportal.domain.repository.RepositoryInterface
import com.example.e_arsivportal.domain.model.CommonStringModel
import com.example.e_arsivportal.domain.model.DocumentModel
import com.example.e_arsivportal.domain.model.GetInvoicesModel
import com.example.e_arsivportal.domain.model.OutgoingInvoiceModel
import com.example.e_arsivportal.presentation.base.BaseViewModel
import com.example.e_arsivportal.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
@HiltViewModel
class OutgoingInvoicesViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : BaseViewModel() {

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
            val resource = repository.getAllOutgoingInvoices(GetInvoicesModel(startDate,endDate))


            when (resource.status) {

                Status.SUCCESS -> {
                    resource.data.let {
                        invoicesliveData.value = it
                        loading.postValue(false)
                    }
                }

                Status.LOADING -> {
                    loading.postValue(true)
                }

                Status.ERROR -> {
                    loading.postValue(false)
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