package com.example.anidb.fragments

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.anidb.R
import com.example.anidb.api.animeById.AnimeById

@Composable
fun Details_top(data: AnimeById){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = data.data?.images?.jpg?.image_url,
                contentDescription = "Image",
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(16)
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(48.dp))
                Text(
                    text = data.data?.title_english?:"No Title",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 4.dp),
                    textAlign = TextAlign.Start
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(4.dp),
                            color = colorResource(R.color.orange)
                        )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.rating_star),
                        contentDescription = null,
                        tint = colorResource(R.color.orange),
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 4.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = data.data?.score?.toString()?:"Not available",
                        color = colorResource(R.color.orange),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(end = 4.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}