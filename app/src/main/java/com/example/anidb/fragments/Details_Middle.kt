package com.example.anidb.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.anidb.R
import com.example.anidb.api.animeById.Data
import com.example.anidb.api.animeById.Genre
import com.example.anidb.viewModels.ApiViewModel

@Composable
fun Details_Middle(data:Data?){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(16.dp)
    ){
        Row(
            modifier = Modifier
                .background(Color.Transparent)
        ) {
            Icon(
                painter = painterResource(R.drawable.year_icon),
                tint = colorResource(R.color.text_gray),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = data?.year.toString(),
                color = colorResource(R.color.text_gray)
            )
            Spacer(modifier = Modifier.width(16.dp))
            VerticalDivider(
                modifier = Modifier
                    .size(24.dp),
                color = colorResource(R.color.text_gray)
            )
            Icon(
                painter = painterResource(R.drawable.duration_icon),
                tint = colorResource(R.color.text_gray),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = data?.duration?:"null",
                color = colorResource(R.color.text_gray)
            )
            Spacer(modifier = Modifier.width(16.dp))
            VerticalDivider(
                modifier = Modifier
                    .size(24.dp),
                color = colorResource(R.color.text_gray)
            )
            Icon(
                painter = painterResource(R.drawable.genre_icon),
                tint = colorResource(R.color.text_gray),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            val genre = data?.genres?.joinToString(",") {
                it?.name?:"Unknown"
            }?:"null"
            //val genres = data?.genres?.map { it?.name }
            Text(
                text = genre,
                color = colorResource(R.color.text_gray),
                maxLines = 1
            )
        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun PreviewMiddle(){
//    Details_Middle()
//}
