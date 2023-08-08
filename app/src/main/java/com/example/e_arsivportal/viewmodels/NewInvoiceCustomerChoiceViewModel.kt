package com.example.e_arsivportal.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_arsivportal.models.CommonStringModel
import com.example.e_arsivportal.models.RecipientDataModel
import com.example.e_arsivportal.repo.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
@HiltViewModel
class NewInvoiceCustomerChoiceViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    private var job : Job? = null

    val liveData = MutableLiveData<RecipientDataModel>()


    fun getDataFromApi(vknTckn : String) {

        val handler = CoroutineExceptionHandler {context, throwable ->
            //Toast.makeText(Context,"Bir sorun meydana geldi",Toast.LENGTH_SHORT).show()
        }

        job = CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = repository.getRecipientData(CommonStringModel(vknTckn))

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