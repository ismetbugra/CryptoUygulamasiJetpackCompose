package com.example.cryptocrazycompose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cryptocrazycompose.data.entity.CryptoListItem
import com.example.cryptocrazycompose.data.repository.CryptoRepository
import com.example.cryptocrazycompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(var crepo:CryptoRepository):ViewModel() {

    var cryptoList= mutableStateOf<List<CryptoListItem>>(listOf())
    var loading= mutableStateOf(false)
    var errorMessage = mutableStateOf("")

    // arama yapılırken bir şey girilmezse ilk çekilen verinin tamamı gosterilmeli o yuzden bunu olusturduk
    var initialCryptoList= listOf<CryptoListItem>()
    var isSearchStarting=true

    init {
        loadCryptos()
    }

    // searchviewdaki  arama algoritması
    fun searchCryptoList(query:String){
        val listToSearch = if(isSearchStarting){
            cryptoList.value
        }else{
            initialCryptoList
        }

        CoroutineScope(Dispatchers.Default).launch {
            if(query.isEmpty()){
                cryptoList.value=initialCryptoList
                isSearchStarting=true
                return@launch
            }

            val results= listToSearch.filter {
                it.currency.contains(query.trim(),ignoreCase = true)
            }

            if(isSearchStarting){
                initialCryptoList=cryptoList.value
                isSearchStarting=false
            }
            cryptoList.value=results
        }
    }

    fun loadCryptos(){
        CoroutineScope(Dispatchers.Main).launch {
            loading.value=true
            var result=crepo.getCryptoList()

            when(result) {
                is Resource.Success -> {

                    errorMessage.value=""
                    loading.value=false
                    cryptoList.value=result.data!!


                }

                is Resource.Error ->{
                    errorMessage.value=result.message!!
                    loading.value=false
                }

                else -> {}
            }


        }

    }
}