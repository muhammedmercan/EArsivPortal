package com.example.e_arsivportal.models

import com.google.gson.annotations.SerializedName

class CreateInvoiceModel (

    @SerializedName("invoice_model")
    val invoiceModel: InvoiceModel,

    @SerializedName("product_list")
    val productList : List<NewInvoiceProductModel>
    ) {
}