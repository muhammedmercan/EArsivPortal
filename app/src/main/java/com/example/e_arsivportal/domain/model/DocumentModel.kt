package com.example.e_arsivportal.domain.model

class DocumentModel (


    var faturaUuid : String,
    var faturaTarihi : String,
    var saat : String,
    var faturaTipi : String,
    var paraBirimi : String,
    var dovzTLkur : String,
    var vknTckn : String,
    var aliciUnvan : String,
    var ulke : String,
    var vergiDairesi : String,
    var belgeNumarasi : String,
    var malHizmetTable : List<NewInvoiceProductModel>,



    ) {
}