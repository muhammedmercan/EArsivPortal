package com.example.e_arsivportal.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_arsivportal.models.ProductModel
import com.example.e_arsivportal.repo.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
@HiltViewModel

class AddProductViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    val liveData = MutableLiveData<Long>()
    val liveDataUpdate = MutableLiveData<Int>()


    fun updateDataInRoom(product: ProductModel) {

        viewModelScope.launch {

            val returnValue = repository.updateProduct(product)
            liveDataUpdate.postValue(returnValue) }

    }

    fun saveDataToRoom(product: ProductModel) {

        viewModelScope.launch {

            val returnValue = repository.addProduct(product)
            liveData.postValue(returnValue)
        }
    }

}