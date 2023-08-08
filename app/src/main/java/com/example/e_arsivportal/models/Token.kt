package com.example.biochakraastralterapi.utilities

object Token
{
    private var token: String? = null

    fun setValue(newToken: String) {
        token = newToken
    }

    fun getValue(): String? {
        return token
    }
}