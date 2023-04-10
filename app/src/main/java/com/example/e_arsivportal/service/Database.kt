package com.example.e_arsivportal.service

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.e_arsivportal.models.CustomerModel
import com.example.e_arsivportal.models.ProductModel


@androidx.room.Database(entities = arrayOf(ProductModel::class, CustomerModel::class),version = 1)
abstract class Database : RoomDatabase() {

    abstract fun dao() : Dao

    //Singleton

    companion object {

        @Volatile private var instance : Database? = null

        private val lock = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }


        private fun makeDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,Database::class.java,"database"
        ).build()
    }
}