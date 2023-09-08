package com.example.e_arsivportal.domain.model

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