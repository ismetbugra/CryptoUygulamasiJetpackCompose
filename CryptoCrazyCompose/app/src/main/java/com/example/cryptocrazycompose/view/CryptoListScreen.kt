package com.example.cryptocrazycompose.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.cryptocrazycompose.data.entity.CryptoListItem
import com.example.cryptocrazycompose.viewmodel.CryptoListViewModel

@Composable
fun CryptoListScreen(navController: NavController,viewModel: CryptoListViewModel= hiltViewModel()){

    Surface(color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.fillMaxSize()) {
        Column {
            Text(text = "Crypto Crazy", modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary)
            
            Spacer(modifier = Modifier.height(10.dp))
            //searchview
            SearchBar(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), hint = "Search..."){
                    viewModel.searchCryptoList(it)
            }
            Spacer(modifier = Modifier.height(10.dp))
            //list
            CryptoList(navController = navController)
        }

    }

}

@Composable
fun SearchBar(modifier: Modifier=Modifier,
              hint:String="",
              onSearch:(String)->Unit={}){
    var text = remember{ mutableStateOf("")}
    var isHintDisplayed = remember{
        mutableStateOf(hint!="")
    }

    Box(modifier = modifier) {
        BasicTextField(value = text.value, onValueChange = {
            text.value= it
            onSearch(it)

        },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    //kullanıcı buaraya tıklamayı bıraktıktan sonra ne olsun
                    isHintDisplayed.value = it.isFocused != true && text.value.isEmpty()
                }
        )

        //hint gösterme işlemi
        if(isHintDisplayed.value){
            Text(text = hint,
                color = Color.LightGray,
                modifier=Modifier.padding(horizontal = 20.dp, vertical = 12.dp))

        }
    }
    




}

// viewmodel işlemleri ve loading işlemi list gosterme işlemi burada
@Composable
fun CryptoList(navController: NavController,viewModel: CryptoListViewModel= hiltViewModel()){

    // 1.yontemle viewmodel alma (viewmodelda coroutinescope kullanarak)
    val cryptoList by remember{
        viewModel.cryptoList
    }
    // by ile yaparsan .value ye ıhtıyac kalmaz

    val loading by remember {
        viewModel.loading
    }

    CryptoListView(navController = navController, cryptos = cryptoList)

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        if (loading){
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun CryptoListView(navController: NavController,cryptos:List<CryptoListItem>){
    LazyColumn(contentPadding = PaddingValues(5.dp)){
        items(cryptos){crypto->
            CryptoRow(navController = navController, crypto = crypto)
        }
    }
}

@Composable
fun CryptoRow(navController: NavController,crypto:CryptoListItem){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        border = BorderStroke(2.dp,MaterialTheme.colorScheme.primary)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.tertiary)
            .padding(10.dp)
            .clickable {
                navController.navigate("crypto_detail_screen/${crypto.currency}/${crypto.price}")
            }) {

            Text(text = crypto.currency,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(2.dp))

            Text(text = crypto.price,
                fontSize = 15.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(2.dp))

        }
    }





}


