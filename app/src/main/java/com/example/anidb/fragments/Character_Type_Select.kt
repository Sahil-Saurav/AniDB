package com.example.anidb.fragments

import androidx.compose.runtime.Composable
import com.example.anidb.items.TypeItem
import com.example.anidb.viewModels.ApiViewModel
import com.example.anidb.viewModels.DetailsViewModel

@Composable
fun Character_Type_Select(
    apiViewModel: ApiViewModel,
    detailsViewModel: DetailsViewModel,
){
    val list = listOf(
        type(1,"Main","Main"),
        type(2,"Supporting","Side")
    )

    list.forEach{
        TypeItem(
            label = it.label,
            checked = detailsViewModel.getIndex()==it.idx,
            onClick = {
                detailsViewModel.setCharacter_Type(it.type)
                detailsViewModel.setIndex(it.idx)
            }
        )
    }
}

data class type(
        val idx:Int,
        val type:String,
        val label:String
        )