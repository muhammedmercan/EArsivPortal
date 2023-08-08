package com.example.e_arsivportal.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    private var job : Job? = null

    val liveData = MutableLiveData<List<IncomingInvoiceModel>>()


    fun getIncomingInvoices(context: Context, startDate:String, endDate:String) {

        val handler = CoroutineExceptionHandler {context, throwable ->
            //Toast.makeText(Context,"Bir sorun meydana geldi",Toast.LENGTH_SHORT).show()
        }

        job = CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = repository.getAllIssuedToMe(GetInvoicesModel(startDate,endDate))

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