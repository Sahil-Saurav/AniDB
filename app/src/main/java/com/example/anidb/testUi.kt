package com.example.anidb

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anidb.viewModels.ApiViewModel

@Composable
fun testApi(viewModel:ApiViewModel){
    var anime by remember {
        mutableStateOf(""   )
    }
    ConstraintLayout (
        modifier = Modifier.fillMaxSize()
    ){
        val (text,button,field) = createRefs()
        Text(
            text="Search your Anime",
            modifier = Modifier
                .constrainAs(text){
                    top.linkTo(parent.top, margin = 64.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        OutlinedTextField(
            value = anime,
            onValueChange = {anime = it},
            Modifier.constrainAs(field){
                top.linkTo(text.bottom, margin = 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        Button(
            onClick = {viewModel.getAnimeSearch(anime,"tv")},
            Modifier.constrainAs(button){
                top.linkTo(field.bottom, margin = 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun testPreview(){
    val viewModel:ApiViewModel = viewModel()
    testApi(viewModel)
}