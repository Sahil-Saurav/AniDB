package com.example.anidb.fragments

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anidb.R
import com.example.anidb.viewModels.ApiViewModel
import com.example.anidb.viewModels.HomeViewModel

@Composable
fun FilterChipsList(apiViewModel: ApiViewModel){

    val homeViewModel: HomeViewModel = viewModel()

    val list = listOf(
        Label(1,"Top Rated", painterResource(R.drawable.popular_icon),"bypopularity",),
        Label(2,"Currently Airing", painterResource(R.drawable.airing_icon),"airing",),
        Label(3,"Upcoming", painterResource(R.drawable.upcoming_icon),"upcoming",),
        Label(4,"Random", painterResource(R.drawable.random_icon),"",)
    )
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(
            items = list, key = {list -> list.idx}
        ){
            list-> FilterChips(list.label,list.icon, selected = homeViewModel.idx.value==list.idx,
            onClick = {
                apiViewModel.getTopAnime(list.search)
                homeViewModel.setLabelDetails(list)
                homeViewModel.setIndex(list.idx)
            }
            )
        }
    }
}

@Composable
fun FilterChips(label: String,icon: Painter,selected:Boolean,onClick:()->Unit){

    FilterChip(
        selected = selected,
        onClick = {onClick()},
        label = { Text(label) },
        leadingIcon = {
            Icon(
            painter = icon,
            contentDescription = label,
            modifier = Modifier
                .size(24.dp)
        )
               },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = colorResource(R.color.card_back),
            labelColor = colorResource(R.color.primary_blue),
            iconColor = colorResource(R.color.primary_blue),
            disabledContainerColor = Color.Unspecified,
            disabledLabelColor = Color.Unspecified,
            disabledLeadingIconColor = colorResource(R.color.primary_blue),
            disabledTrailingIconColor = colorResource(R.color.primary_blue),
            selectedContainerColor = colorResource(R.color.card_back),
            disabledSelectedContainerColor = colorResource(R.color.card_back),
            selectedLabelColor = colorResource(R.color.orange),
            selectedLeadingIconColor = colorResource(R.color.orange),
            selectedTrailingIconColor = colorResource(R.color.orange)
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if(selected) colorResource(R.color.orange) else colorResource(R.color.primary_blue)
        ),
        shape = RoundedCornerShape(64),
        modifier = Modifier
            .height(32.dp)
            .padding(
                start = 8.dp,
                end = 8.dp)
    )
}

@Composable
@Preview(showBackground = true)
fun UIPREVIEW(){
    FilterChips("Popular", painterResource(R.drawable.popular_icon),true,{})
}

data class Label (
    val idx:Int,
    val label:String,
    val icon : Painter,
    val search:String,
        )
