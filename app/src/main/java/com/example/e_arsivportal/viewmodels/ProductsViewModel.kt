package com.example.e_arsivportal.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_arsivportal.models.ProductModel
import com.example.e_arsivportal.repo.RepositoryInterface
import com.example.e_arsivportal.service.Database
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    private var job : Job? = null
    val liveData = MutableLiveData<MutableList<ProductModel>>()


    fun getDataFromRoom(context: Context) {

        job = CoroutineScope(Dispatchers.IO).launch {
            val produtcs = Database(context).dao().getAllProducts()

            liveData.postValue(produtcs)

            //liveData.value = produtcs

        }
    }

    fun deleteProduct(id: Int, context: Context) {

        job = CoroutineScope(Dispatchers.IO).launch {
            Database(context).dao().deleteProduct(id)

        }

    }

}