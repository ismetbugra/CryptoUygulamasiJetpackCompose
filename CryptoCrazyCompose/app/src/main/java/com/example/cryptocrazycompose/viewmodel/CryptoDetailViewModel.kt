package com.example.cryptocrazycompose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cryptocrazycompose.data.entity.CryptoItem
import com.example.cryptocrazycompose.data.repository.CryptoRepository
import com.example.cryptocrazycompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(var crepo:CryptoRepository):ViewModel() {



    suspend fun getCrypto(id:String):Resource<List<CryptoItem>> {
        return crepo.getCrypto()
    }
}