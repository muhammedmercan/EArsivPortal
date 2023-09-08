package com.example.e_arsivportal.domain.model

import com.google.gson.annotations.SerializedName

class NewInvoiceProductModel (

    @SerializedName("malHizmet")
    var name: String = "",

    @SerializedName("miktar")
    var quantity : Int = 0,

    //@SerializedName("birim")
    //var unit : String ="",

    @SerializedName("birimFiyat")
    var unitPrice : Double = 0.0,

    @SerializedName("kdvOrani")
    var vatRate : Int = 0,

    @SerializedName("kdvTutari")
    var vatAmount : Double = 0.0,

    @SerializedName("iskontoOrani")
    var discountRate : Int = 0,

    @SerializedName("iskontoTutari")
    var discountAmount : Double = 0.0,



    //@SerializedName("customTaxName")
    //var customTaxName : String = "",

    //@SerializedName("customTaxRate")
    //var customTaxRate : Int = 0,

    //@SerializedName("customTaxAmount")
    //var customTaxAmount : Double = 0.0,

    //@SerializedName("gtip")
    //var rawAmount : Double = 0.0,

    //@SerializedName("gtip")
    //var taxAmount : Double = 0.00,

    //@SerializedName("gtip")
    //var grandTotal : Double = 0.0,




    ) : java.io.Serializable {


}