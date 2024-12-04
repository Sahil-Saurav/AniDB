package com.example.anidb.fragments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.anidb.Utils.Loading
import com.example.anidb.api.NetworkResponse
import com.example.anidb.items.ReviewItems
import com.example.anidb.viewModels.ApiViewModel

@Composable
fun ReviewList(viewModel: ApiViewModel){
    val animeReview = viewModel.animeReview.observeAsState()
    var limit by remember {
        mutableStateOf(10)
    }
    when(val result = animeReview.value){
        is NetworkResponse.Loading->{
            Loading("Loading")
        }
        is NetworkResponse.Error ->{
            Text(result.message)
        }
        is NetworkResponse.Success ->{
            val list = result.data.data?.take(limit)?:emptyList()
            LazyColumn {
                items(list){
                    item->  ReviewItems(item)
                }
                item {
                    if(limit<=list.size && list.isNotEmpty()){
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = "Load More",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    limit+=10
                                }
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
        null ->{
            Text("No Data is Available")}
    }
}