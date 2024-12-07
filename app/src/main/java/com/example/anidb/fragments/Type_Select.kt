package com.example.anidb.fragments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anidb.items.TypeItem
import com.example.anidb.viewModels.ApiViewModel
import com.example.anidb.viewModels.SearchViewModel

@Composable
fun Type_Select(viewModel: SearchViewModel){
    val apiViewModel = viewModel<ApiViewModel>()
    val list = listOf(
        typeSelect(1,"tv"),
        typeSelect(2,"movie")
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        list.forEach {
            TypeItem(
                label=it.type,
                checked=viewModel.idx.value==it.idx,
                onClick ={
                    viewModel.setIndex(it.idx)
                    viewModel.setType(it.type)
                    if(viewModel.search.value.isNotEmpty()){
                        apiViewModel.getAnimeSearch(viewModel.getSearch(),viewModel.getType())
                    }
                }
            )
        }
    }
}

data class typeSelect (
    val idx:Int,
    val type:String
)
