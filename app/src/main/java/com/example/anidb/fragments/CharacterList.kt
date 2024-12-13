package com.example.anidb.fragments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anidb.Utils.Loading
import com.example.anidb.api.NetworkResponse
import com.example.anidb.items.CharacterItem
import com.example.anidb.viewModels.ApiViewModel
import com.example.anidb.viewModels.DetailsViewModel

@Composable
fun CharacterList(viewModel: ApiViewModel){
    val detailsViewModel = viewModel<DetailsViewModel>()
    val animeCharacter = viewModel.animeCharacter.observeAsState()
    var limit by remember {
        mutableStateOf(10)
    }
    when(val result = animeCharacter.value){
        is NetworkResponse.Success ->{
            val list = result.data.data?.filter{it.role==detailsViewModel.getCharacter_Type()}?:emptyList()
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Character_Type_Select(viewModel,detailsViewModel)
            }
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2)
            ) {

                items(list){
                    item-> CharacterItem(item)
                }
            }
        }
        is NetworkResponse.Error -> {
            Text("Error occurred")
        }
        NetworkResponse.Loading ->{
            Loading("Loading")
        }
        null -> {
            Text("Found Nothing")
        }
    }
}