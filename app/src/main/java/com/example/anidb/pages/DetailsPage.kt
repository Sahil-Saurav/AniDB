package com.example.anidb.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.anidb.Navigation
import com.example.anidb.R
import com.example.anidb.Utils.Topbar
import com.example.anidb.api.NetworkResponse
import com.example.anidb.api.animeById.AnimeById
import com.example.anidb.api.animeById.Data
import com.example.anidb.fragments.CharacterList
import com.example.anidb.fragments.Details_Bottom
import com.example.anidb.fragments.Details_Middle
import com.example.anidb.fragments.Details_top
import com.example.anidb.fragments.ReviewList
import com.example.anidb.fragments.Youtube_Player
import com.example.anidb.items.About
import com.example.anidb.items.RecommendationItems
import com.example.anidb.items.ReviewItems
import com.example.anidb.viewModels.ApiViewModel
import com.example.anidb.viewModels.DetailsViewModel
import com.example.anidb.viewModels.HomeViewModel

@Composable
fun DetailsPage(viewModel: ApiViewModel,navController: NavHostController){
    val animeDetailsById = viewModel.animeDetailsById.observeAsState()

    val detailsViewModel : DetailsViewModel = viewModel()
    Surface(modifier = Modifier
        .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.pageBack))
        ) {
            Topbar(
                title = "Details",
                onBackClick = {navController.navigateUp()}
            )
            Spacer(modifier = Modifier.height(16.dp))
            when(val result = animeDetailsById.value){
                is NetworkResponse.Error ->{
                    Text(result.message?:"An unexpected Error Occurred")
                }
                is NetworkResponse.Loading ->{
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent)
                    ) {
                        CircularProgressIndicator(
                            color = colorResource(R.color.orange)
                        )
                    }
                }
                is NetworkResponse.Success ->{
                    Details_top(result.data)
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyRow {
                        item{
                            Details_Middle(result.data.data)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Details_Bottom(detailsViewModel)
                    Spacer(modifier = Modifier.height(16.dp))
                    when(detailsViewModel.toDo.value){
                        "About" ->{
                            LazyColumn {
                                item {
                                    About(result.data.data)
                                }
                            }
                        }
                        "Review" ->{
                            viewModel.getAnimeReview(result.data.data?.mal_id)
                            ReviewList(viewModel)
                        }
                        "Trailer" ->{
                            val videoId = result.data.data?.trailer?.youtube_id
                            if(!videoId.isNullOrEmpty()){
                                Youtube_Player(videoId)
                            }else{
                                Text(
                                    text = "Trailer Not Available",
                                    color = Color.White,
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    textDecoration = TextDecoration.Underline,
                                    fontWeight = FontWeight.SemiBold,
                                    fontStyle = FontStyle.Italic
                                )
                            }
                        }
                        "Characters" ->{
                            viewModel.getAnimeCharacters(result.data.data?.mal_id)
                            CharacterList(viewModel)
                        }
                    }
                }
                null -> {
                    Text("No Details Available")
                }
            }
        }
    }
}