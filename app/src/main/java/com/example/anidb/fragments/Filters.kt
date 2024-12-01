package com.example.anidb.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.anidb.R
import com.example.anidb.api.NetworkResponse
import com.example.anidb.items.FilterItems
import com.example.anidb.viewModels.ApiViewModel

@Composable
fun Filters(viewModel: ApiViewModel){
    val topAnime = viewModel.animeTop.observeAsState()
    Column(
        modifier= Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(val result = topAnime.value){
            is NetworkResponse.Loading ->{
               CircularProgressIndicator(
                   color = colorResource(R.color.orange)
               )
            }
            is NetworkResponse.Error ->{
                Text(result.message?:"An unexpected Error occurred")
            }
            is NetworkResponse.Success ->{
                LazyHorizontalStaggeredGrid(
                    rows = StaggeredGridCells.Fixed(2),
                    modifier = Modifier
                        .height(560.dp)
                        .background(color = Color.Transparent)
                ) {
                    result.data.data.let {
                        items(it?:emptyList()){
                            item->
                            FilterItems(item,viewModel)
                        }
                    }
                    }
                }
            null ->{
                Text("No Items available")
            }
        }
    }
}

