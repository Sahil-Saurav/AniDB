package com.example.anidb.fragments

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ChipColors
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.anidb.R
import com.example.anidb.viewModels.ApiViewModel
import com.example.anidb.viewModels.HomeViewModel

@Composable
fun FilterChipsList(apiViewModel: ApiViewModel){
    val homeViewModel: HomeViewModel = viewModel()
    val list = listOf(
        Label("Top Rated", painterResource(R.drawable.popular_icon),"bypopularity"),
        Label("Currently Airing", painterResource(R.drawable.airing_icon),"airing"),
        Label("Upcoming", painterResource(R.drawable.upcoming_icon),"upcoming"),
        Label("Random", painterResource(R.drawable.random_icon),"")
    )
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(list){
            item-> FilterChips(item.label,item.icon,
            onClick = {
                apiViewModel.getTopAnime(item.search)
                homeViewModel.getLabelDetails(item)
            })
        }
    }
}

@Composable
fun FilterChips(label: String,icon: Painter,onClick:()->Unit){
    SuggestionChip(
        onClick = {onClick()},
        label = { Text(label) },
        icon = {
            Icon(
            painter = icon,
            contentDescription = label,
            tint = colorResource(R.color.orange),
            modifier = Modifier
                .size(24.dp)
        )
               },
        colors = ChipColors(
            containerColor = colorResource(R.color.card_back),
            labelColor = colorResource(R.color.orange),
            leadingIconContentColor = Color.Unspecified,
            trailingIconContentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,
            disabledLabelColor = Color.Unspecified,
            disabledLeadingIconContentColor = Color.Unspecified,
            disabledTrailingIconContentColor = Color.Unspecified
        ),
        border = BorderStroke(
            width = 1.dp,
            color = colorResource(R.color.orange)
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
    FilterChips("Popular", painterResource(R.drawable.popular_icon),{})
}

data class Label (
    val label:String,
    val icon : Painter,
    val search:String
        )
