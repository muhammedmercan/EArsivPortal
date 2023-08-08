package com.example.biochakraastralterapi.utilities

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class CustomSharedPreferences {

    companion object {

        private var sharedPreferences: SharedPreferences? = null

        @Volatile private var instance: CustomSharedPreferences? = null
        private val lock = Any()

        operator fun invoke(context: Context) : CustomSharedPreferences = instance ?: synchronized(lock) {
            instance ?: makeCustomSharedPreferences(context).also {
                instance = it
            }
        }

        private fun makeCustomSharedPreferences(context: Context) : CustomSharedPreferences {
            sharedPreferences = context?.getSharedPreferences("mainPreference",Context.MODE_PRIVATE)
            return CustomSharedPreferences()
        }

    }

    fun saveUser(username:String, password:String) {
        sharedPreferences?.edit(commit = true) {
            putString("username",username)
            putString("password",password)
        }
    }

    fun deleteUser() {
        sharedPreferences?.edit()?.clear()?.apply()
    }


    fun getUsername() = sharedPreferences?.getString("username","")

    fun getPassword() = sharedPreferences?.getString("password","")


}