package com.example.cryptocrazycompose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.cryptocrazycompose.data.entity.CryptoItem
import com.example.cryptocrazycompose.util.Resource
import com.example.cryptocrazycompose.viewmodel.CryptoDetailViewModel

@Composable
fun CryptoDetailScreen(
    id:String,
    price:String,
    navController: NavController,
    viewModel: CryptoDetailViewModel = hiltViewModel()){

    /* effect handlerda ilk yontem
    var cryptoItem by remember {
        mutableStateOf<Resource<List<CryptoItem>>>(Resource.Loading())
    }

    // viewmodelda suspend fun kullanırsak
    LaunchedEffect(key1 = Unit){
        cryptoItem=viewModel.getCrypto(id)
    }*/

    // effect handlerda ikinci yöntem
    val cryptoItem by produceState<Resource<List<CryptoItem>>>(initialValue = Resource.Loading()){
        value=viewModel.getCrypto(id)
    }
    
    
    
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.tertiary),
        contentAlignment = Alignment.Center){

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            when(cryptoItem){
                is Resource.Success ->{
                    val selectedCrypto = cryptoItem.data!![0]
                    Text(text = selectedCrypto.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        modifier=Modifier.padding(5.dp))
                    
                    Image(painter = rememberImagePainter(data = selectedCrypto.logo_url),
                        contentDescription = selectedCrypto.name,
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(CircleShape)
                            .size(200.dp, 200.dp)
                            .border(2.dp, Color.Black, CircleShape))

                    Text(text = price,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        modifier=Modifier.padding(5.dp))
                }

                is Resource.Loading -> {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }

                is Resource.Error -> {
                    Text(text = cryptoItem.message!!)
                }
            }

        }
    }

}