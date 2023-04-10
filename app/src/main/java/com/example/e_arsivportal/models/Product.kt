package com.example.e_arsivportal.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Products")
class ProductModel (

    @ColumnInfo("name")
    var name: String,

    @ColumnInfo("unit")
    var unit: String,

    @ColumnInfo("price")
    var price: Float,

    @ColumnInfo("vatRate")
    var vatRate: Int

        ) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}