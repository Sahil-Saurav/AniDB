package com.example.anidb.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.anidb.api.animeById.Data

@Composable
fun About(data:Data?){
    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
    ) {
        Text(
            text = data?.synopsis?:"No Data Available",
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
    }
}