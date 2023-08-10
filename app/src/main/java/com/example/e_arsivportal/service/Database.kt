package com.example.e_arsivportal.service

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.e_arsivportal.models.CustomerModel
import com.example.e_arsivportal.models.ProductModel


@androidx.room.Database(entities = arrayOf(ProductModel::class, CustomerModel::class),version = 1)
abstract class Database : RoomDatabase() {

    abstract fun dao() : Dao

}