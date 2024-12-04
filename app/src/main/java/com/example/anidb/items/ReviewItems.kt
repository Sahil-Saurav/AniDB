package com.example.anidb.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.anidb.R
import com.example.anidb.api.animeReview.Data

@Composable
fun ReviewItems(data:Data?){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp,end = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
            ) {
                AsyncImage(
                    model = data?.user?.images?.jpg?.image_url,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(80.dp),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = data?.score.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = colorResource(R.color.orange),
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column() {
                Text(
                    text = data?.user?.username?:"Redacted",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = colorResource(R.color.primary_blue),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = data?.review?:"Not available",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

//@Composable
//@Preview(showBackground = true)
//fun reviewPreview(){
//    ReviewItems()
//}
