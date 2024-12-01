package com.example.anidb.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ChipColors
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.anidb.R
import com.example.anidb.api.recomendationAnime.Entry
import com.example.anidb.viewModels.ApiViewModel


@Composable
fun RecommendationItems(data:Entry?,viewModel: ApiViewModel){
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable { viewModel.getAnimeFullById(data?.mal_id) },
        colors = CardColors(
            containerColor = colorResource(R.color.card_back),
            contentColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified
        )
    ) {
        Column(
            modifier = Modifier
                .border(width = 2.dp,
                    color = colorResource(R.color.primary_blue),
                    shape = RoundedCornerShape(13.dp)
                    )
                .width(166.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            AsyncImage(
                model = data?.images?.jpg?.image_url,
                contentDescription = null,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .padding(top = 8.dp)
            )
            Spacer(modifier = Modifier
                .height(8.dp))
            Text(
                text = data?.title?:"No title",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
                    .size(166.dp)
                    .padding(4.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ItemPreview(){

}