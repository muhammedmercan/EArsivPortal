package com.example.e_arsivportal.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Customers" )
class CustomerModel (

    @ColumnInfo("vkn_tckn")
    var vknTckn: String,

    @ColumnInfo("title")
    var title: String,

    @ColumnInfo("name")
    var name: String,

    @ColumnInfo("surname")
    var surname: String,

    @ColumnInfo("tax_department")
    var taxDepartment: String,

    @ColumnInfo("adress")
    var adress: String,

) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}