package com.example.e_arsivportal.domain.model

data class OutgoingInvoiceModel(
    val belgeNumarasi: String,
    val belgeTarihi: String,
    val belgeTuru: String,
    val ettn: String,
    val onayDurumu: String,
    val aliciUnvanAdSoyad: String,
    val aliciVknTckn: String
)