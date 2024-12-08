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
import com.example.anidb.Utils.Show_Full_Review
import com.example.anidb.api.NetworkResponse
import com.example.anidb.api.animeReview.Data
import com.example.anidb.items.ReviewItems
import com.example.anidb.viewModels.ApiViewModel

@Composable
fun ReviewList(viewModel: ApiViewModel){
    val animeReview = viewModel.animeReview.observeAsState()
    var limit by remember {
        mutableStateOf(10)
    }
    var showFullReview by remember {
        mutableStateOf(false)
    }
    var reviewToShow:Data? by remember {
        mutableStateOf(null)
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
                    item->  ReviewItems(item, onClick = {
                        showFullReview=true
                        reviewToShow=item
                    })
                }
                item {
                    if(list.size>=limit && list.isNotEmpty()){
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
            if(showFullReview){
                Show_Full_Review(
                    data = reviewToShow, onDismiss = {showFullReview=false})
            }
        }
        null ->{
            Text("No Data is Available")}
    }
}