package com.example.e_arsivportal.data.local.roomdb

import androidx.room.RoomDatabase
import com.example.e_arsivportal.domain.model.CustomerModel
import com.example.e_arsivportal.domain.model.ProductModel


@androidx.room.Database(entities = arrayOf(ProductModel::class, CustomerModel::class),version = 1)
abstract class Database : RoomDatabase() {

    abstract fun dao() : Dao

}