package com.example.anidb.Utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.anidb.R
import com.example.anidb.api.animeCharacter.Data
import com.example.anidb.api.animeCharacter.Person
import com.example.anidb.api.animeCharacter.VoiceActor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Show_Voice_Actors(
    data:Person?,
    onDismiss:()->Unit
){
    BasicAlertDialog(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                color = colorResource(R.color.pageBack),
                shape = RoundedCornerShape(32.dp)
            ),
        onDismissRequest = {onDismiss()}
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    top = 16.dp,
                    bottom = 16.dp
                    )
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Voice Actor",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    color = Color.White)
                AsyncImage(
                    model = data?.images?.jpg?.image_url,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(80.dp),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = data?.name?:"Not available",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = colorResource(R.color.orange),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
