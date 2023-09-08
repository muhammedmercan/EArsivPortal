package com.example.e_arsivportal.domain.model

class InvoiceItemModel (

    val malHizmet : String,
    val miktar : String,
    // val birim : String,
    val birimFiyat : Float,
    val kdvOrani : Float,
    val iskontoOrani : Float,
    val iskontoTipi : String,
    val iskontoNedeni : String,
        ) {
}