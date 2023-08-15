package com.example.e_arsivportal.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_arsivportal.models.GetInvoicesModel
import com.example.e_arsivportal.models.IncomingInvoiceModel
import com.example.e_arsivportal.repo.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class IncomingInvoicesViewModel @Inject constructor(
        private val repository: RepositoryInterface
) : ViewModel() {

    val liveData = MutableLiveData<List<IncomingInvoiceModel>>()


    fun getIncomingInvoices(startDate:String, endDate:String) {

        val handler = CoroutineExceptionHandler {context, throwable ->
            //Toast.makeText(Context,"Bir sorun meydana geldi",Toast.LENGTH_SHORT).show()
        }

        viewModelScope.launch(handler) {
            val response = repository.getAllIssuedToMe(GetInvoicesModel(startDate,endDate))

            if(response.isSuccessful) {
                response.body()?.let {

                    liveData.value = it

                    }
                }
            }
        }
    }
