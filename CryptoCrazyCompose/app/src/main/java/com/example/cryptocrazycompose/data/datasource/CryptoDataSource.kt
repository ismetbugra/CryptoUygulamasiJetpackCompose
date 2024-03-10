package com.example.cryptocrazycompose.data.datasource

import com.example.cryptocrazycompose.data.entity.CryptoItem
import com.example.cryptocrazycompose.data.entity.CryptoListItem
import com.example.cryptocrazycompose.retrofit.CryptoAPI
import com.example.cryptocrazycompose.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ActivityScoped
class CryptoDataSource @Inject constructor(var cdao:CryptoAPI) {

    suspend fun getCryptoList():Resource<List<CryptoListItem>> =
        withContext(Dispatchers.IO){
            val response=try{
                cdao.getCryptoList()
            }catch (e:Exception){
                return@withContext Resource.Error("Error")
            }
            return@withContext Resource.Success(response)
        }

    suspend fun getCrypto():Resource<List<CryptoItem>>
    = withContext(Dispatchers.IO){
        val response=try {
            cdao.getCrypto()
        }catch (e:Exception){
            return@withContext Resource.Error("Error")
        }
        return@withContext Resource.Success(response)
    }
}