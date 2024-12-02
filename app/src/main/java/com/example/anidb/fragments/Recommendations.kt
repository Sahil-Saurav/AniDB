package com.example.anidb.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.anidb.R
import com.example.anidb.Utils.Loading
import com.example.anidb.api.NetworkResponse
import com.example.anidb.items.RecommendationItems
import com.example.anidb.viewModels.ApiViewModel

@Composable
fun Recommendations(viewModel: ApiViewModel,navController: NavHostController){
    val animeRecommendations = viewModel.animeRecommendations.observeAsState()
    Column(
        modifier= Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(val result = animeRecommendations.value){
            is NetworkResponse.Error ->{
                Text(result.message?:"An unexpected Error Occurred")
            }

            NetworkResponse.Loading ->{
                Loading(text = "Getting Recommendations")
            }
            is NetworkResponse.Success ->{

                LazyHorizontalStaggeredGrid(
                    rows = StaggeredGridCells.Fixed(1),
                    modifier = Modifier
                        .height(280.dp)
                        .background(color = Color.Transparent)
                ) {
                    result.data.data?.forEach {
                        items(it?.entry?:emptyList()){
                                item ->
                            RecommendationItems(item,viewModel,navController)
                        }
                    }
                }
            }
            null -> {
                Text("No Recommendations Available")
            }
        }
    }
}