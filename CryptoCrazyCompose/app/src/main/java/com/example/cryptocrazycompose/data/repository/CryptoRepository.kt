package com.example.cryptocrazycompose.data.repository

import com.example.cryptocrazycompose.data.datasource.CryptoDataSource
import com.example.cryptocrazycompose.data.entity.CryptoItem
import com.example.cryptocrazycompose.data.entity.CryptoListItem
import com.example.cryptocrazycompose.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CryptoRepository @Inject constructor(var cdso:CryptoDataSource) {

    suspend fun getCryptoList(): Resource<List<CryptoListItem>> = cdso.getCryptoList()
    suspend fun getCrypto():Resource<List<CryptoItem>> = cdso.getCrypto()

}