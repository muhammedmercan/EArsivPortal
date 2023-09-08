package com.example.e_arsivportal.util

import androidx.lifecycle.MutableLiveData
import com.example.e_arsivportal.domain.model.NewInvoiceProductModel

object DataHolder {
    val productList: MutableLiveData<MutableList<NewInvoiceProductModel>> = MutableLiveData()
    const val BASE_URL = "https://mercanmakina.net/earsiv-fatura/examples/"

}