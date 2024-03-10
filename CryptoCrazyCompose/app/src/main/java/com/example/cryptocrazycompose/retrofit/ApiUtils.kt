package com.example.cryptocrazycompose.retrofit

import retrofit2.create

class ApiUtils {
    companion object{
        val BASE_URL="https://raw.githubusercontent.com/"
        fun getCryptoDao():CryptoAPI{
            return RetrofitClient.getClient(BASE_URL).create()
        }
    }
}