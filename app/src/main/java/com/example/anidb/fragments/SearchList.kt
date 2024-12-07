package com.example.anidb.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.anidb.R
import com.example.anidb.Utils.Loading
import com.example.anidb.Utils.Search_Util
import com.example.anidb.api.NetworkResponse
import com.example.anidb.items.SearchItems
import com.example.anidb.viewModels.ApiViewModel

@Composable
fun SearchList(viewModel: ApiViewModel,navController: NavController){
    val animeDetailsbySearch = viewModel.animeDetailsBySearch.observeAsState()
    var limit by remember {
        mutableStateOf(5)
    }
    Column(
        modifier= Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when(val result = animeDetailsbySearch.value){
            is NetworkResponse.Success ->{
                val list = result.data.data?.take(limit)?: emptyList()
                LazyColumn(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                ) {
                    if (!list.isNullOrEmpty()){
                        items(list){ item ->
                            SearchItems(viewModel,item,navController)
                        }
                    }else{
                        item {
                            Search_Util(
                                message = "We Are Sorry, We Cannot Find The Anime :(",
                                support = "Find your anime by title and type"
                            )
                        }
                    }
                    item {
                        if(list.size>=limit){
                            Button(
                                onClick = {limit+=5},
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonColors(
                                    containerColor = colorResource(R.color.primary_blue),
                                    contentColor = Color.White,
                                    disabledContainerColor = Color.Unspecified,
                                    disabledContentColor = Color.Unspecified
                                )
                            ) {
                                Text("Load More")
                            }
                        }
                    }
                }
            }
            is NetworkResponse.Error ->{
                Text(result.message)
            }
            NetworkResponse.Loading -> {
                Loading("Loading")
            }
            null -> {
                Search_Util(
                    message = "Search your favourite Anime",
                    support = "Find your anime by title and type"
                )
            }
        }
    }
}