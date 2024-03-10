package com.example.cryptocrazycompose.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object{
        val BASE_URL="https://raw.githubusercontent.com/"

        fun getClient(baseurl:String):Retrofit{
            return Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}