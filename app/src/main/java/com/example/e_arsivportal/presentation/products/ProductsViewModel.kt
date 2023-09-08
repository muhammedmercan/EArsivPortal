package com.example.e_arsivportal.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_arsivportal.domain.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    //val liveData = MutableLiveData<MutableList<ProductModel>>()

    val liveData = repository.getAllProducts()

/*
    fun getDataFromRoom() {

        viewModelScope.launch {
            val produtcs = repository.getAllProducts()
            liveData.postValue(produtcs)
            //liveData.value = produtcs
        }
    }


 */
    fun deleteProduct(id: Int) {

        viewModelScope.launch {
            repository.deleteProduct(id)

        }
    }
}