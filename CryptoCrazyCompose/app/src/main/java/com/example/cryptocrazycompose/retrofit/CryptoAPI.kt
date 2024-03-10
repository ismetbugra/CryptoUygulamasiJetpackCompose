package com.example.cryptocrazycompose.retrofit

import com.example.cryptocrazycompose.data.entity.CryptoItem
import com.example.cryptocrazycompose.data.entity.CryptoListItem
import retrofit2.http.GET

interface CryptoAPI {

    @GET("atilsamancioglu/IA32-CryptoComposeData/main/cryptolist.json")
    suspend fun getCryptoList():List<CryptoListItem>

    @GET("atilsamancioglu/IA32-CryptoComposeData/main/crypto.json")
    suspend fun getCrypto():List<CryptoItem>
}