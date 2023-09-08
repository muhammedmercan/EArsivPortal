package com.example.e_arsivportal.presentation.incominginvoices

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.e_arsivportal.domain.model.GetInvoicesModel
import com.example.e_arsivportal.domain.model.IncomingInvoiceModel
import com.example.e_arsivportal.domain.repository.RepositoryInterface
import com.example.e_arsivportal.presentation.base.BaseViewModel
import com.example.e_arsivportal.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class IncomingInvoicesViewModel @Inject constructor(
        private val repository: RepositoryInterface
) : BaseViewModel() {

    val liveData = MutableLiveData<List<IncomingInvoiceModel>>()


    fun getIncomingInvoices(startDate:String, endDate:String) {

        val handler = CoroutineExceptionHandler {context, throwable ->
            //Toast.makeText(Context,"Bir sorun meydana geldi",Toast.LENGTH_SHORT).show()
        }

        viewModelScope.launch(handler) {
            val resource = repository.getAllIssuedToMe(GetInvoicesModel(startDate,endDate))

            when (resource.status) {

                Status.SUCCESS -> {
                    resource.data.let {
                        liveData.value = it
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
    }
