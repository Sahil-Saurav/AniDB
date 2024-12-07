package com.example.anidb.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.anidb.R
import com.example.anidb.Screens
import com.example.anidb.api.animeSearch.AnimeDetailsbySearch
import com.example.anidb.api.animeSearch.Data
import com.example.anidb.viewModels.ApiViewModel

@Composable
fun SearchItems(viewModel: ApiViewModel,data:Data?,navController:NavController){

    Card(
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified),
        modifier = Modifier
            .border(
                width = 1.dp,
                color = colorResource(R.color.primary_blue),
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                viewModel.getAnimeFullById(data?.mal_id)
                navController.navigate(Screens.DetailsScreen.route)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = data?.images?.jpg?.image_url,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = if(!data?.title_english.isNullOrEmpty()) data?.title_english?:"Not available" else data?.title?:"Not available",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.background(Color.Transparent)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    Icon(
                        painter = painterResource(R.drawable.rating_star),
                        contentDescription = null,
                        tint = colorResource(R.color.orange)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = data?.score.toString(),
                        color = colorResource(R.color.orange),
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Row {
                    Icon(
                        painter = painterResource(R.drawable.genre_icon),
                        contentDescription = null,
                        tint = colorResource(R.color.text_gray)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    val genres = data?.genres?.map { it?.name }?.first()
                    Text(
                        text = genres?:"not available",
                        color = colorResource(R.color.text_gray),
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Row {
                    Icon(
                        painter = painterResource(R.drawable.year_icon),
                        contentDescription = null,
                        tint = colorResource(R.color.text_gray)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = data?.year.toString(),
                        color = colorResource(R.color.text_gray),
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Row {
                    Icon(
                        painter = painterResource(R.drawable.duration_icon),
                        contentDescription = null,
                        tint = colorResource(R.color.text_gray)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = data?.duration.toString(),
                        color = colorResource(R.color.text_gray),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}

