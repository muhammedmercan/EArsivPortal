package com.example.e_arsivportal.models

data class IncomingInvoiceModel(
    val belgeNumarasi: String,
    val belgeTarihi: String,
    val belgeTuru: String,
    val ettn: String,
    val onayDurumu: String,
    val saticiUnvanAdSoyad: String,
    val saticiVknTckn: String
)