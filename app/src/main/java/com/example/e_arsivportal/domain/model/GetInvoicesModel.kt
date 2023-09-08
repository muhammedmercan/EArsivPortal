package com.example.e_arsivportal.domain.model

import com.google.gson.annotations.SerializedName

class GetInvoicesModel(

    @SerializedName("start_date")
    val startDate : String,

    @SerializedName("end_date")
    val endDate : String,

) {
}