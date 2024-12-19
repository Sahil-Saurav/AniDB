package com.example.anidb.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ChipColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.anidb.R
import com.example.anidb.Screens
import com.example.anidb.Utils.CustomToast
import com.example.anidb.api.recomendationAnime.Entry
import com.example.anidb.viewModels.ApiViewModel
import com.example.anidb.viewModels.WatchList


@Composable
fun WatchListItems(data:WatchList,onMenuClick:()->Unit){
    var showDropDown by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(4.dp)
            .border(width = 2.dp,
                color = colorResource(R.color.primary_blue),
                shape = RoundedCornerShape(13.dp)
            )
            .height(240.dp),
        colors = CardColors(
            containerColor = colorResource(R.color.card_back),
            contentColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified
        )
    ) {
        Box{
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, end = 2.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    tint = colorResource(R.color.orange),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { showDropDown = true }
                )
                DropdownMenu(
                    expanded = showDropDown,
                    onDismissRequest = {showDropDown = false},
                    modifier = Modifier
                        .height(64.dp)
                        .background(color = colorResource(R.color.pageBack))
                ) {
                    DropdownMenuItem(
                        text = { Text(
                            text = "Remove",
                            fontSize = 16.sp
                            )},
                        leadingIcon = { Icon(Icons.Default.Clear,null)},
                        colors = MenuItemColors(
                            textColor = colorResource(R.color.primary_blue),
                            leadingIconColor = colorResource(R.color.primary_blue),
                            trailingIconColor = Color.Unspecified,
                            disabledTextColor = Color.Unspecified,
                            disabledLeadingIconColor = Color.Unspecified,
                            disabledTrailingIconColor = Color.Unspecified
                        ),
                        onClick = {
                            showDropDown = false
                            CustomToast(context,"Removed from watchlist")
                            onMenuClick()
                        }
                    )
                }
            }
            Column(
                modifier = Modifier
                    .width(166.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                AsyncImage(
                    model = data.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(16.dp))
                        .padding(top = 8.dp)
                )
                Spacer(modifier = Modifier
                    .height(8.dp))
                Text(
                    text = data.animeName?:"Not Available",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(4.dp),
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
}