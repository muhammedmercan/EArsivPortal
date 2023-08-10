package com.example.e_arsivportal.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_arsivportal.models.ProductModel
import com.example.e_arsivportal.repo.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    val liveData = MutableLiveData<MutableList<ProductModel>>()

    fun getDataFromRoom() {

        viewModelScope.launch {
            val produtcs = repository.getAllProducts()
            liveData.postValue(produtcs)
            //liveData.value = produtcs
        }
    }

    fun deleteProduct(id: Int) {

        viewModelScope.launch {
            repository.deleteProduct(id)

        }
    }
}