package com.example.e_arsivportal.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_arsivportal.models.*
import com.example.e_arsivportal.repo.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
@HiltViewModel
class NewInvoiceProductsViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {


    val liveData = MutableLiveData<String>()

    fun createInvoice(invoice: CreateInvoiceModel) {

        val handler = CoroutineExceptionHandler {context, throwable ->
            //Toast.makeText(Context,"Bir sorun meydana geldi",Toast.LENGTH_SHORT).show()
            println(throwable)
        }

        viewModelScope.launch(handler) {
            val response = repository.createInvoice(invoice)

            if(response.isSuccessful) {
                response.body()?.let {
                    liveData.value = it
                }
            }
        }
    }
}