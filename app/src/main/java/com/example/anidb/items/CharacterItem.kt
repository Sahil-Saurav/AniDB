package com.example.anidb.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.anidb.R
import com.example.anidb.api.animeCharacter.Data

@Composable
fun CharacterItem(data:Data){
    Card(
        modifier = Modifier
            .background(Color.Transparent)
            .wrapContentSize(),
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(120.dp)
                .height(200.dp)
        ) {
            AsyncImage(
                model = data.character?.images?.jpg?.image_url,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(128.dp),
                contentScale = ContentScale.FillBounds,

            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = data.character?.name?:"name",
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun characterPreview(){
//    CharacterItem()
//}